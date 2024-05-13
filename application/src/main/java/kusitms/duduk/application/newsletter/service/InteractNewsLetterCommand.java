package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.port.input.InteractNewsLetterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InteractNewsLetterCommand implements InteractNewsLetterUseCase {

    @Override
    public void increaseViewCount(Long id) {

    }

    @Override
    public void increaseScrapCount(Long id) {

    }
}
