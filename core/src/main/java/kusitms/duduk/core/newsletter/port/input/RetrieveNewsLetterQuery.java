package kusitms.duduk.core.newsletter.port.input;

import kusitms.duduk.core.newsletter.dto.request.RetrieveNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;

public interface RetrieveNewsLetterQuery {
    NewsLetterResponse retrieve(RetrieveNewsLetterRequest request);
}
