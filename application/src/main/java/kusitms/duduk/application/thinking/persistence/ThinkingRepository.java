package kusitms.duduk.application.thinking.persistence;

import kusitms.duduk.application.thinking.persistence.entity.ThinkingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThinkingRepository extends JpaRepository<ThinkingJpaEntity, Long> {

}
