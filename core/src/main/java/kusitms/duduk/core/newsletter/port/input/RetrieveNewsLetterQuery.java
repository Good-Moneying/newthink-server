package kusitms.duduk.core.newsletter.port.input;

import java.util.List;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.domain.user.User;

public interface RetrieveNewsLetterQuery {

    NewsLetterDetailResponse retrieveNewsLetterDetail(String email, Long newsLetterId);

    NewsLetterThumbnailResponse retrieveLatestNewsLetter(User user);

    List<NewsLetterThumbnailResponse> retrieveRealtimeTrendNewsLetter(User user);

    List<NewsLetterThumbnailResponse> retrieveCustomizeNewsLetter(User user);
}