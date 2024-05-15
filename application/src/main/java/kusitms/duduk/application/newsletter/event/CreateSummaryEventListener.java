package kusitms.duduk.application.newsletter.event;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.newsletter.dto.response.NaverClovaSummaryResponse;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.NaverClovaSummaryClientPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateSummaryEventListener {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final NaverClovaSummaryClientPort naverClovaSummaryClientPort;
    private final UpdateNewsLetterPort updateNewsLetterPort;

    @EventListener
    public void createSummary(CreateSummaryEvent event) {
        NewsLetter newsLetter = loadNewsLetterPort.findById(event.getNewsLetterId())
            .orElseThrow(() -> new NotExistsException("NewsLetter not found"));

        NaverClovaSummaryResponse response = naverClovaSummaryClientPort.summarize(newsLetter);
        newsLetter.addSummary(response.summary());

//        newsLetter.addSummary("섬머리된 정보");
        updateNewsLetterPort.update(newsLetter);
    }

}
