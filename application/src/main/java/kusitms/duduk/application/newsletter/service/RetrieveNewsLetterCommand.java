package kusitms.duduk.application.newsletter.service;

import java.util.List;
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
        // 유저를 불러와야 합니다.
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        NewsLetter newsLetter = loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new IllegalArgumentException("해당 뉴스레터를 찾을 수 없습니다."));

        // 에디터의 정보를 불러옵니다.
        User editor = null;
        if (newsLetter.getEditorId().getValue() != null) {
            editor = loadUserPort.findById(newsLetter.getEditorId().getValue())
	.orElseThrow(() -> new IllegalArgumentException("해당 에디터를 찾을 수 없습니다."));
        }

        // 추가로 해당 뉴스레터에 대한 코멘트를 다 불러옵니다. 불러오는 조건을 좋아요 수로 합시다
        List<Comment> comments = loadCommentPort.findByNewsLetterIdOrderByLikeCountDesc(
            newsLetterId);

        // 코멘트 작성 여부를 확인합니다.
        boolean isCommented = loadCommentPort.isExistByNewsLetterIdAndUserId(newsLetterId,
            user.getId().getValue());

        return NewsLetterDetailResponse.builder()
            .title(newsLetter.getTitle().getTitle())
            .publishedAt(newsLetter.getCreatedAt())
            .isCommented(isCommented)
            .summary(newsLetter.getSummary().toSentence())

            // Todo : AI 뉴스레터 body 값을 어떻게 할 것인지 논의해야 합니다.
            .body(newsLetter.getContent().getContent() != null ? parseNewsLetterCommand.parseContentToBlocks(newsLetter)
	: null)

            .editor(editor != null ?
	Editor.builder()
	    .nickname(editor.getNickname().getValue())
	    .profileUrl(editor.getLevel().getProfileUrl())
	    .build() : null)

            .comments(comments.stream()
	.map(comment -> NewsLetterDetailResponse.Comment.builder()
	    .userId(comment.getUserId().getValue())
	    .content(comment.getContent().getSentence())
	    .likeCount(comment.getLikeCount().getCount())
	    .build())
                .toList())
            .build();
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