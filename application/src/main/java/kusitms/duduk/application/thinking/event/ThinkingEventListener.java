package kusitms.duduk.application.thinking.event;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import kusitms.duduk.application.comment.event.CreateCommentEvent;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.comment.port.output.LoadCommentPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.core.thinking.port.input.CreateThinkingUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ThinkingEventListener {

    private final LoadCommentPort loadCommentPort;
    private final LoadUserPort loadUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;

    private final CreateThinkingUseCase createThinkingUseCase;

    @EventListener
    @Transactional
    public void handleCreateCommentEvent(CreateCommentEvent event) {
        log.info("CreateCommentEvent 시작");
        Comment comment = loadCommentById(event.getCommentId());
        User user = loadUserById(comment);
        NewsLetter newsLetter = loadNewsLetterById(comment);

        createThinkingWithComment(user, newsLetter, comment);
    }

    private NewsLetter loadNewsLetterById(Comment comment) {
        NewsLetter newsLetter = loadNewsLetterPort.findById(comment.getNewsLetterId().getValue())
            .orElseThrow(() -> new NotExistsException(NEWS_LETTER_NOT_FOUND.getMessage()));
        return newsLetter;
    }

    private User loadUserById(Comment comment) {
        User user = loadUserPort.findById(comment.getUserId().getValue())
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
        return user;
    }

    private void createThinkingWithComment(User user, NewsLetter newsLetter, Comment comment) {

        CreateThinkingRequest request = CreateThinkingRequest.builder()
            .userId(user.getId().getValue())
            .newsLetterId(newsLetter.getId().getValue())
            .thumbnail(newsLetter.getThumbnail().getUrl())
            .keywords(newsLetter.getKeywords().toSentence())
            .comment(comment.getSentence().getValue())
            .summarizedComment(comment.getSummarizedSentence().getValue())
            .build();

        createThinkingUseCase.create(request);
    }

    private Comment loadCommentById(Long commentId) {
        return loadCommentPort.findById(commentId)
            .orElseThrow(() -> new NotExistsException("Comment not found. ID: " + commentId));
    }
}
