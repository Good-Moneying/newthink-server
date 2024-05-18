package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.port.input.InteractNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class InteractNewsLetterCommand implements InteractNewsLetterUseCase {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final SaveNewsLetterPort saveNewsLetterPort;

    @Override
    public void increaseViewCount(Long id) {
        log.debug("IncreaseViewCount Start() id : {}", id);
        loadNewsLetterPort.findById(id).ifPresent(newsLetter -> {
            newsLetter.increaseViewCount();
            saveNewsLetterPort.saveAndFlush(newsLetter);
        });

    }

    @Override
    public void increaseScrapCount(Long id) {
        log.debug("IncreaseScrapCount Start() id : {}", id);
        loadNewsLetterPort.findById(id).ifPresent(newsLetter -> {
            newsLetter.increaseScrapCount();
            saveNewsLetterPort.saveAndFlush(newsLetter);
        });
    }
}
