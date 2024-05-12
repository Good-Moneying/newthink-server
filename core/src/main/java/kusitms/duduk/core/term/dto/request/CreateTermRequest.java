package kusitms.duduk.core.term.dto.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record CreateTermRequest(
    String koreanName,
    String englishName,
    String description,
    String category
) {

}
