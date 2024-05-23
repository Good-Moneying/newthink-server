package kusitms.duduk.application.newsletter.event;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.newsletter.dto.response.NaverClovaSummaryResponse;
import kusitms.duduk.core.newsletter.port.input.InteractNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.NaverClovaClientPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsLetterEventListener {

    private final InteractNewsLetterUseCase interactNewsLetterUseCase;
    private final LoadNewsLetterPort loadNewsLetterPort;
    private final NaverClovaClientPort<NewsLetter, NaverClovaSummaryResponse> naverClovaClientPort;
    private final UpdateNewsLetterPort updateNewsLetterPort;


    @EventListener
    public void increaseViewCount(RetrieveNewsLetterEvent event) {
        log.info("increaseViewCount 이벤트 발생");
        interactNewsLetterUseCase.increaseViewCount(event.getId());
    }

    @EventListener
    public void increaseScrapCount(ArchiveNewsLetterEvent event) {
        log.info("increaseScrapCount 이벤트 발생");
        interactNewsLetterUseCase.increaseScrapCount(event.getId());
    }

    @EventListener
    public void createSummary(CreateSummaryEvent event) {
        log.info("CreateSummary 이벤트 발생");

        NewsLetter newsLetter = loadNewsLetterPort.findById(event.getNewsLetterId())
            .orElseThrow(() -> new NotExistsException(NEWS_LETTER_NOT_FOUND.getMessage()));

        NaverClovaSummaryResponse response = naverClovaClientPort.execute(newsLetter);
        log.info("네이버 클로바 3줄 요약 : {}", response.summary());

        newsLetter.addSummary(response.summary());
        updateNewsLetterPort.update(newsLetter);
    }
}
