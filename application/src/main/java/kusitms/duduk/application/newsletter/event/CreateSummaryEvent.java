package kusitms.duduk.application.newsletter.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateSummaryEvent extends ApplicationEvent {

    private final Long newsLetterId;

    public CreateSummaryEvent(Object source, Long newsLetterId) {
        super(source);
        this.newsLetterId = newsLetterId;
    }
}
