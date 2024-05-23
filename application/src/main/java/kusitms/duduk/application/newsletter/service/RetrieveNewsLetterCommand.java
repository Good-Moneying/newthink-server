package kusitms.duduk.application.newsletter.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import java.util.List;
import kusitms.duduk.application.newsletter.event.RetrieveNewsLetterEvent;
import kusitms.duduk.common.exception.ErrorMessage;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.comment.port.output.LoadCommentPort;
import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse.Editor;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.core.newsletter.port.input.RetrieveNewsLetterQuery;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class RetrieveNewsLetterCommand implements RetrieveNewsLetterQuery {

    private final LoadUserPort loadUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;
    private final LoadCommentPort loadCommentPort;
    private final NewsLetterDtoMapper newsLetterDtoMapper;
    private final ParseNewsLetterCommand parseNewsLetterCommand;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public NewsLetterDetailResponse retrieveNewsLetterDetail(String email, Long newsLetterId) {
        log.info("뉴스레터 상세 조회를 시작합니다. email: {}, newsLetterId: {}", email, newsLetterId);

        publishRetrieveEvent(email, newsLetterId);
        User user = loadUserByEmail(email);
        NewsLetter newsLetter = loadNewsLetterById(newsLetterId);
        User editor = loadEditor(newsLetter);
        List<Comment> comments = getCommentsByNewsLetterId(newsLetterId);
        boolean isCommented = isUserCommented(newsLetterId, user);

        return buildNewsLetterDetailResponse(user, newsLetter, editor, comments, isCommented);
    }

    private void publishRetrieveEvent(String email, Long newsLetterId) {
        applicationEventPublisher.publishEvent(new RetrieveNewsLetterEvent(email, newsLetterId));
    }

    private User loadUserByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
    }

    private NewsLetter loadNewsLetterById(Long newsLetterId) {
        return loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException(NEWS_LETTER_NOT_FOUND.getMessage()));
    }

    private User loadEditor(NewsLetter newsLetter) {
        if (newsLetter.getEditorId().getValue() == null) {
            return null;
        }

        return loadUserPort.findById(newsLetter.getEditorId().getValue())
            .orElseThrow(() -> new NotExistsException(EDITOR_NOT_FOUND.getMessage()));
    }

    private List<Comment> getCommentsByNewsLetterId(Long newsLetterId) {
        return loadCommentPort.findByNewsLetterIdOrderByLikeCountDesc(newsLetterId);
    }

    private boolean isUserCommented(Long newsLetterId, User user) {
        return loadCommentPort.isExistByNewsLetterIdAndUserId(newsLetterId,
            user.getId().getValue());
    }

    private NewsLetterDetailResponse buildNewsLetterDetailResponse(User user, NewsLetter newsLetter,
        User editor, List<Comment> comments, boolean isCommented) {
        return NewsLetterDetailResponse.builder()
            .title(newsLetter.getTitle().getTitle())
            .publishedAt(newsLetter.getCreatedAt())
            .isCommented(isCommented)
            .summary(newsLetter.getSummary().toSentence())
            .body(parseContent(newsLetter))
            .editor(buildEditorResponse(editor))
            .comments(buildCommentsResponse(comments))
            .build();
    }

    private List<NewsLetterDetailResponse.Block> parseContent(NewsLetter newsLetter) {
        return newsLetter.getContent().getContent() != null
            ? parseNewsLetterCommand.parseContentToBlocks(newsLetter)
            : null;
    }

    private Editor buildEditorResponse(User editor) {
        if (editor == null) {
            return null;
        }

        return Editor.builder()
            .nickname(editor.getNickname().getValue())
            .profileUrl(editor.getLevel().getProfileUrl())
            .build();
    }

    private List<NewsLetterDetailResponse.Comment> buildCommentsResponse(List<Comment> comments) {
        return comments.stream()
            .map(this::buildCommentResponse)
            .toList();
    }

    private NewsLetterDetailResponse.Comment buildCommentResponse(Comment comment) {
        User user = loadUserById(comment.getUserId().getValue());

        return NewsLetterDetailResponse.Comment.builder()
            .userLevel(user.getLevel().name())
            .userNickname(user.getNickname().getValue())
            .userProfileUrl(user.getLevel().getProfileUrl())
            .perspective(comment.getPerspective().getDescription())
            .content(comment.getSentence().getValue())
            .likeCount(comment.getLikeCount().getCount())
            .build();
    }

    private User loadUserById(Long userId) {
        return loadUserPort.findById(userId)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
    }

    public NewsLetterThumbnailResponse retrieveLatestNewsLetter(User user) {
        NewsLetter newsLetter = loadNewsLetterPort.findLatestNewsLetter()
            .orElseThrow(() -> new NotExistsException(NEWS_LETTER_NOT_FOUND.getMessage()));

        boolean isScrapped = user.isScrapped(newsLetter);
        return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
    }

    @Override
    public List<NewsLetterThumbnailResponse> retrieveRealtimeTrendNewsLetter(User user) {
        return loadNewsLetterPort.findRealtimeTrendNewsLetters().stream()
            .map(newsLetter -> mapToThumbnailResponse(newsLetter, user))
            .toList();
    }

    @Override
    public List<NewsLetterThumbnailResponse> retrieveCustomizeNewsLetter(User user) {
        return loadNewsLetterPort.findCustomizeNewsLetters(user.getCategory()).stream()
            .map(newsLetter -> mapToThumbnailResponse(newsLetter, user))
            .toList();
    }

    private NewsLetterThumbnailResponse mapToThumbnailResponse(NewsLetter newsLetter, User user) {
        boolean isScrapped = user.isScrapped(newsLetter);
        return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
    }
}
