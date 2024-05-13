package kusitms.duduk.application.newsletter.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ArchiveNewsLetterEvent extends ApplicationEvent {

    private final Long id;

    public ArchiveNewsLetterEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }
}
