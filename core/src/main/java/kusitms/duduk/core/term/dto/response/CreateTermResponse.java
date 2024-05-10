package kusitms.duduk.core.term.dto.response;

import lombok.Builder;

@Builder
public record CreateTermResponse(Long termId, String koreanName) {

}
