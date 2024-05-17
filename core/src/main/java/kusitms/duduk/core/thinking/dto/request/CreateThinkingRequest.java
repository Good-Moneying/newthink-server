package kusitms.duduk.core.thinking.dto.request;

import java.util.List;
import lombok.Builder;

@Builder
public record CreateThinkingRequest(Long userId,
		    Long newsLetterId,
		    String comment,
		    String summarizedComment,
		    String thumbnail) {

}
