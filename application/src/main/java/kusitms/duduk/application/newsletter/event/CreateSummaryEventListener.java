package kusitms.duduk.application.newsletter.event;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.newsletter.dto.response.NaverClovaSummaryResponse;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.NaverClovaSummaryClientPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateSummaryEventListener {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final NaverClovaSummaryClientPort naverClovaSummaryClientPort;
    private final UpdateNewsLetterPort updateNewsLetterPort;

    @EventListener
    public void createSummary(CreateSummaryEvent event) {
        log.info("CreateSummaryEventListener Start(). Request : {}", event.toString());

        NewsLetter newsLetter = loadNewsLetterPort.findById(event.getNewsLetterId())
            .orElseThrow(() -> new NotExistsException("NewsLetter not found"));

        NaverClovaSummaryResponse response = naverClovaSummaryClientPort.summarize(newsLetter);
        newsLetter.addSummary(response.summary());

        log.info("NaverClovaSummaryResponse : {}", response.summary());

        updateNewsLetterPort.update(newsLetter);
    }

}
