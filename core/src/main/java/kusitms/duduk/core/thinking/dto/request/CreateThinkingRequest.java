package kusitms.duduk.core.thinking.dto.request;

import lombok.Builder;

@Builder
public record CreateThinkingRequest(Long userId,
		    Long newsLetterId,
		    String comment,
		    String keywords,
		    String summarizedComment,
		    String thumbnail) {

}
