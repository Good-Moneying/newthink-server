package kusitms.duduk.application.term.persistence;

import java.util.Optional;
import kusitms.duduk.application.term.persistence.entity.TermJpaEntity;
import kusitms.duduk.domain.term.Term;

public interface TermRepositoryCustom {
    Optional<TermJpaEntity> findLatestTerm();
}
