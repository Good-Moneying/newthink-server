package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.port.input.InteractNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InteractNewsLetterCommand implements InteractNewsLetterUseCase {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final SaveNewsLetterPort saveNewsLetterPort;

    @Override
    public void increaseViewCount(Long id) {
        loadNewsLetterPort.findById(id).ifPresent(newsLetter -> {
            newsLetter.increaseViewCount();
            saveNewsLetterPort.saveAndFlush(newsLetter);
        });

    }

    @Override
    public void increaseScrapCount(Long id) {
        loadNewsLetterPort.findById(id).ifPresent(newsLetter -> {
            newsLetter.increaseScrapCount();
            saveNewsLetterPort.saveAndFlush(newsLetter);
        });
    }
}
