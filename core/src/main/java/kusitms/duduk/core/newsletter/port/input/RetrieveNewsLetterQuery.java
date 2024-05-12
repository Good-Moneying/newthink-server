package kusitms.duduk.core.newsletter.port.input;

import kusitms.duduk.core.newsletter.dto.request.RetrieveNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.domain.user.User;

public interface RetrieveNewsLetterQuery {
    NewsLetterResponse retrieve(RetrieveNewsLetterRequest request);
    NewsLetterThumbnailResponse retriveLatestNewsLetter(User user);
}
