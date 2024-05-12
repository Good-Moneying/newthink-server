package kusitms.duduk.domain.term;

import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.term.vo.TermCategory;
import kusitms.duduk.domain.term.vo.Description;
import kusitms.duduk.domain.term.vo.Name;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Term {

    private Id id;
    private Name koreanName;
    private Name englishName;
    private TermCategory termCategory;
    private Description description;
}
