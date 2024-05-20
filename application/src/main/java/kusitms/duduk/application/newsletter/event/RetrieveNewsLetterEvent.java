package kusitms.duduk.application.newsletter.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RetrieveNewsLetterEvent extends ApplicationEvent {

    private final Long id;

    public RetrieveNewsLetterEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }
}
