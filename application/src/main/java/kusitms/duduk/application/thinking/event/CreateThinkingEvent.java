package kusitms.duduk.application.thinking.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateThinkingEvent extends ApplicationEvent {

    private final Long thinkingId;
    private final Long userId;

    public CreateThinkingEvent(Object source, Long thinkingId, Long userId) {
        super(source);
        this.thinkingId = thinkingId;
        this.userId = userId;
    }
}
