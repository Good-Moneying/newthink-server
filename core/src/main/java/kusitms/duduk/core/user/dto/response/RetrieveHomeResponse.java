package kusitms.duduk.core.user.dto.response;

import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import lombok.Builder;

@Builder(toBuilder = true)
public record RetrieveHomeResponse(NewsLetterThumbnailResponse todayNewsLetter) {

}
