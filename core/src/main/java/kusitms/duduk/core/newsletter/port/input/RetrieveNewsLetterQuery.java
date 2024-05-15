package kusitms.duduk.core.newsletter.port.input;

import java.util.List;
import kusitms.duduk.core.newsletter.dto.request.RetrieveNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.domain.user.User;

public interface RetrieveNewsLetterQuery {

    NewsLetterResponse retrieveNewsLetterDetail(RetrieveNewsLetterRequest request);

    NewsLetterThumbnailResponse retrieveLatestNewsLetter(User user);

    List<NewsLetterThumbnailResponse> retrieveRealtimeTrendNewsLetter(User user);

    List<NewsLetterThumbnailResponse> retrieveCustomizeNewsLetter(User user);
}
