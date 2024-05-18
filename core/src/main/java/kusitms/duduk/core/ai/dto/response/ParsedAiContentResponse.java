package kusitms.duduk.core.ai.dto.response;

import lombok.Builder;

@Builder
public record ParsedAiContentResponse(String title, String content, String keywords, String category) {

}
