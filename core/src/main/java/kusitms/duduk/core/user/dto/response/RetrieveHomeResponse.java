package kusitms.duduk.core.user.dto.response;

import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import lombok.Builder;

@Builder(toBuilder = true)
public record RetrieveHomeResponse(NewsLetterThumbnailResponse todayNewsLetter,
		   		   RetrieveTermResponse todayTerm) {

}
