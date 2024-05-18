package kusitms.duduk.application.newsletter.event;

import kusitms.duduk.core.newsletter.port.input.InteractNewsLetterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewsLetterEventListener {

    private final InteractNewsLetterUseCase interactNewsLetterUseCase;

    @EventListener
    public void increaseViewCount(RetrieveNewsLetterEvent event) {
        interactNewsLetterUseCase.increaseViewCount(event.getId());
    }

    @EventListener
    public void increaseScrapCount(ArchiveNewsLetterEvent event) {
        interactNewsLetterUseCase.increaseScrapCount(event.getId());
    }
}
