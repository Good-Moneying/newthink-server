package kusitms.duduk.core.thinking.dto.response;

import java.util.List;
import lombok.Builder;

@Builder(toBuilder = true)
public record RetrieveThinkingHomeResponse(
    List<RetrieveThinkingDetailResponse> thinkingDetails
) {

}
