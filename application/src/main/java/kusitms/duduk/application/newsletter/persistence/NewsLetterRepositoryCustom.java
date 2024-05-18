package kusitms.duduk.application.newsletter.persistence;

import java.util.List;
import java.util.Optional;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.domain.global.Category;

public interface NewsLetterRepositoryCustom {
    Optional<NewsLetterJpaEntity> findLatestNewsLetter();

    List<NewsLetterJpaEntity> findRealtimeTrendNewsLetters();

    List<NewsLetterJpaEntity> findCustomizeNewsLetters(Category category);
}
