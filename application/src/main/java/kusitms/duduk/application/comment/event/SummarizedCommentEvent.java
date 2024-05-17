package kusitms.duduk.application.comment.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SummarizedCommentEvent extends ApplicationEvent {

    private final Long id;

    public SummarizedCommentEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }
}
