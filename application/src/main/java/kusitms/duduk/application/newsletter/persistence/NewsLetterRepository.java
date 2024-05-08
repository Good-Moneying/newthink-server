package kusitms.duduk.application.newsletter.persistence;

import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsLetterRepository extends JpaRepository<NewsLetterJpaEntity, Long> {
}
