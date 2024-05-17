package kusitms.duduk.application.comment.event;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.comment.port.input.SummarizeCommentUseCase;
import kusitms.duduk.core.comment.port.output.LoadCommentPort;
import kusitms.duduk.core.comment.port.output.UpdateCommentPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
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
public class SummarizedCommentEventListener {

    private final SummarizeCommentUseCase summarizeCommentUseCase;

    private final LoadCommentPort loadCommentPort;
    private final UpdateCommentPort updateCommentPort;

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final UpdateNewsLetterPort updateNewsLetterPort;

    @EventListener
    @Transactional
    public void handleSummarizeCommentEvent(SummarizedCommentEvent event) {
        Comment comment = loadCommentPort.findById(event.getId())
            .orElseThrow(() -> new NotExistsException("Comment not existed. newsLetterId: " + event.getId()));

        String summarizedContent = summarizeCommentUseCase.summarize(comment);
        comment.addSummarizedContent(summarizedContent);
        updateCommentPort.update(comment);

        User user = loadUserPort.findById(comment.getUserId().getValue())
            .orElseThrow(() -> new NotExistsException(
	"User not existed. newsLetterId: " + comment.getUserId().getValue()));
        NewsLetter newsLetter = loadNewsLetterPort.findById(comment.getNewsLetterId().getValue())
            .orElseThrow(() -> new NotExistsException(
	"NewsLetter not existed. newsLetterId: " + comment.getNewsLetterId().getValue()));

        user.addComment(comment);
        newsLetter.addComment(comment);

        updateUserPort.update(user);
        updateNewsLetterPort.update(newsLetter);
        updateCommentPort.update(comment);
    }
}