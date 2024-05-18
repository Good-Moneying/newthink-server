package kusitms.duduk.application.newsletter.event;

import kusitms.duduk.core.newsletter.port.input.InteractNewsLetterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsLetterEventListener {

    private final InteractNewsLetterUseCase interactNewsLetterUseCase;

    @EventListener
    public void increaseViewCount(RetrieveNewsLetterEvent event) {
        log.debug("RetrieveNewsLetterEvent increaseViewCount: {}", event.getId());
        interactNewsLetterUseCase.increaseViewCount(event.getId());
    }

    @EventListener
    public void increaseScrapCount(ArchiveNewsLetterEvent event) {
        log.debug("RetrieveNewsLetterEvent increaseScrapCount: {}", event.getId());
        interactNewsLetterUseCase.increaseScrapCount(event.getId());
    }
}
