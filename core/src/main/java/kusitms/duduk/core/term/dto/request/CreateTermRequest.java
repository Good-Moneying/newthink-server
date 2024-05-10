package kusitms.duduk.core.term.dto.request;

import kusitms.duduk.domain.term.vo.TermCategory;

public record CreateTermRequest(
    String koreanName,
    String englishName,
    String description,
    String category
) {

}
