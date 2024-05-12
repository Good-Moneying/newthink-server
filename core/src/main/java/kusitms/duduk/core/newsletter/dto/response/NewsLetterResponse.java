package kusitms.duduk.core.newsletter.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record NewsLetterResponse(String title, String content, String keywords, String category,
		 String summary, String type) {
}
