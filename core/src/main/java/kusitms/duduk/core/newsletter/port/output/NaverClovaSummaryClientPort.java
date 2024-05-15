package kusitms.duduk.core.newsletter.port.output;

import kusitms.duduk.core.newsletter.dto.response.NaverClovaSummaryResponse;
import kusitms.duduk.domain.newsletter.NewsLetter;

public interface NaverClovaSummaryClientPort {

    NaverClovaSummaryResponse summarize(NewsLetter newsLetter);
}
