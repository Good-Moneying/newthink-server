package kusitms.duduk.apiserver.newsletter.event;

import kusitms.duduk.application.newsletter.event.RetrieveNewsLetterEvent;
import kusitms.duduk.core.newsletter.port.input.InteractNewsLetterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RetrieveEventListener {

    private final InteractNewsLetterUseCase interactNewsLetterUseCase;

    @EventListener
    public void increaseViewCount(RetrieveNewsLetterEvent event) {
        interactNewsLetterUseCase.increaseViewCount(event.getId());
    }
}
