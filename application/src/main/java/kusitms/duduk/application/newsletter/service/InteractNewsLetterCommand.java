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
        log.info("조회수가 증가합니다. newsLetterId : {}", id);
        loadNewsLetterPort.findById(id).ifPresent(newsLetter -> {
            newsLetter.increaseViewCount();
            saveNewsLetterPort.saveAndFlush(newsLetter);
        });

    }

    @Override
    public void increaseScrapCount(Long id) {
        log.info("뉴스레터를 아카이브 합니다. newsLetterId : {}", id);
        loadNewsLetterPort.findById(id).ifPresent(newsLetter -> {
            newsLetter.increaseScrapCount();
            saveNewsLetterPort.saveAndFlush(newsLetter);
        });
    }
}
