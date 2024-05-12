package kusitms.duduk.application.term.persistence;

import kusitms.duduk.application.term.persistence.entity.TermJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<TermJpaEntity, Long> {

}
