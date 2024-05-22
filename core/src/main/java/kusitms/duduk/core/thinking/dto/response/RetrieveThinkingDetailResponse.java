package kusitms.duduk.core.thinking.dto.response;

import java.util.List;
import lombok.Builder;

@Builder(toBuilder = true)
public record RetrieveThinkingDetailResponse(
    Long thinkingId,
    Long userId,
    Long newsLetterId,
    String thumbnailUrl,
    String keywords,
    String comment,
    String summarizedComment,
    boolean isCloudExist,
    List<String> thinkingCloud
) {

}
