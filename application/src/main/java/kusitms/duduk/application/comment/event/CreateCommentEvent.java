package kusitms.duduk.application.comment.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateCommentEvent extends ApplicationEvent {

    private final Long commentId;
    private final Long userId;

    public CreateCommentEvent(Object source, Long commentId, Long userId) {
        super(source);
        this.commentId = commentId;
        this.userId = userId;
    }
}
