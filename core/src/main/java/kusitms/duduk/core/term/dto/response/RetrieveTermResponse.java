package kusitms.duduk.core.term.dto.response;

import lombok.Builder;

@Builder
public record RetrieveTermResponse(
    Long termId,
    String englishName,
    String koreanName,
    String description,
    String category,
    boolean isScrapped
) {

}
