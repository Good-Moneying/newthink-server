package kusitms.duduk.application.newsletter.service;

import java.util.List;
import kusitms.duduk.application.newsletter.event.RetrieveNewsLetterEvent;
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
        log.info("RetrieveNewsLetterDetail Start()\n");

        applicationEventPublisher.publishEvent(new RetrieveNewsLetterEvent(email, newsLetterId));

        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("해당 유저를 찾을 수 없습니다."));
        log.info("RetrieveNewsLetterDetail user : {}\n", user);

        NewsLetter newsLetter = loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException("해당 뉴스레터를 찾을 수 없습니다."));

        log.info("RetrieveNewsLetterDetail newsLetter : {}\n", newsLetter);

        User editor = null;
        if (newsLetter.getEditorId().getValue() != null) {
            log.info("RetrieveNewsLetterDetail editorId : {}\n", newsLetter.getEditorId().getValue());

            editor = loadUserPort.findById(newsLetter.getEditorId().getValue())
	.orElseThrow(() -> new IllegalArgumentException("해당 에디터를 찾을 수 없습니다."));
        }

        List<Comment> comments = loadCommentPort.findByNewsLetterIdOrderByLikeCountDesc(
            newsLetterId);
        log.info("RetrieveNewsLetterDetail comments size : {}\n", comments.size());

        boolean isCommented = loadCommentPort.isExistByNewsLetterIdAndUserId(newsLetterId,
            user.getId().getValue());

        log.info("RetrieveNewsLetterDetail isCommented {}\n", isCommented);

        return NewsLetterDetailResponse.builder()
            .title(newsLetter.getTitle().getTitle())
            .publishedAt(newsLetter.getCreatedAt())
            .isCommented(isCommented)
            .summary(newsLetter.getSummary().toSentence())

            .body(newsLetter.getContent().getContent() != null
	? parseNewsLetterCommand.parseContentToBlocks(newsLetter)
	: null)

            .editor(editor != null ?
	Editor.builder()
	    .nickname(editor.getNickname().getValue())
	    .profileUrl(editor.getLevel().getProfileUrl())
	    .build() : null)

            .comments(comments.stream()
	.map(comment -> {
	    User loadUser = loadUser(comment.getUserId().getValue());
	    return NewsLetterDetailResponse.Comment.builder()
	        .userLevel(user.getLevel().name()) // user의 level
	        .userNickname(user.getNickname().getValue()) // user의 nickname
	        .userProfileUrl(user.getLevel().getProfileUrl()) // user의 profile URL
	        .perspective(comment.getPerspective().getDescription())
	        .content(comment.getSentence().getValue())
	        .likeCount(comment.getLikeCount().getCount())
	        .build();
	})
	.toList())
            .build();
    }

    private User loadUser(Long userId) {
        return loadUserPort.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
    }

    public NewsLetterThumbnailResponse retrieveLatestNewsLetter(User user) {
        NewsLetter newsLetter = loadNewsLetterPort.findLatestNewsLetter()
            .orElseThrow(() -> new IllegalArgumentException("오늘의 뉴스레터를 찾을 수 없습니다."));

        boolean isScrapped = user.isScrapped(newsLetter);
        return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
    }

    @Override
    public List<NewsLetterThumbnailResponse> retrieveRealtimeTrendNewsLetter(User user) {
        return loadNewsLetterPort.findRealtimeTrendNewsLetters().stream()
            .map(newsLetter -> {
	boolean isScrapped = user.isScrapped(newsLetter);
	return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
            })
            .toList();
    }

    @Override
    public List<NewsLetterThumbnailResponse> retrieveCustomizeNewsLetter(User user) {
        return loadNewsLetterPort.findCustomizeNewsLetters(user.getCategory())
            .stream()
            .map(newsLetter -> {
	boolean isScrapped = user.isScrapped(newsLetter);
	return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
            })
            .toList();
    }
}