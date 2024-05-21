package kusitms.duduk.application.comment.event;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.comment.port.input.SummarizeCommentUseCase;
import kusitms.duduk.core.comment.port.output.LoadCommentPort;
import kusitms.duduk.core.comment.port.output.UpdateCommentPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.core.thinking.port.input.CreateThinkingUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentEventListener {

    private final SummarizeCommentUseCase summarizeCommentUseCase;

    private final LoadCommentPort loadCommentPort;
    private final UpdateCommentPort updateCommentPort;

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final UpdateNewsLetterPort updateNewsLetterPort;

    private final CreateThinkingUseCase createThinkingUseCase;

    @EventListener
    @Transactional
    public void handleCreateCommentEvent(CreateCommentEvent event) {
        Comment comment = findCommentById(event.getCommentId());
        String summarizedContent = summarizeComment(comment);

        User user = loadUserPort.findById(comment.getUserId().getValue())
            .orElseThrow(() -> new NotExistsException(
	"User not found. ID: " + comment.getUserId().getValue()));

        NewsLetter newsLetter = loadNewsLetterPort.findById(comment.getNewsLetterId().getValue())
            .orElseThrow(() -> new NotExistsException(
	"Newsletter not found. ID: " + comment.getNewsLetterId().getValue()));

        createThinkingWithComment(user, newsLetter, comment, summarizedContent);

        updateComment(comment, summarizedContent);
        updateUser(user, comment);
        updateNewsLetter(newsLetter, comment);
    }

    // Comment 작성 시 Thinking 생성
    private void createThinkingWithComment(User user, NewsLetter newsLetter, Comment comment,
        String summarizedContent) {

        CreateThinkingRequest request = CreateThinkingRequest.builder()
            .userId(user.getId().getValue())
            .newsLetterId(newsLetter.getId().getValue())
            .thumbnail(newsLetter.getThumbnail().getUrl())
            .comment(comment.getSentence().getValue())
            .summarizedComment(summarizedContent)
            .build();

        createThinkingUseCase.create(request);
    }

    private Comment findCommentById(Long commentId) {
        return loadCommentPort.findById(commentId)
            .orElseThrow(() -> new NotExistsException("Comment not found. ID: " + commentId));
    }

    private String summarizeComment(Comment comment) {
        return summarizeCommentUseCase.summarize(comment);
    }

    private void updateComment(Comment comment, String summarizedContent) {
        comment.addSummarizedContent(summarizedContent);
        updateCommentPort.update(comment);
    }

    private void updateUser(User user, Comment comment) {
        user.addComment(comment);
        updateUserPort.update(user);
    }

    private void updateNewsLetter(NewsLetter newsLetter, Comment comment) {
        newsLetter.addComment(comment);
        updateNewsLetterPort.update(newsLetter);
    }
}
