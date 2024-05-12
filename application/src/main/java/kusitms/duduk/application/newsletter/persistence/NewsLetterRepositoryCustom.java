package kusitms.duduk.application.newsletter.persistence;

import java.util.Optional;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;

public interface NewsLetterRepositoryCustom {
    Optional<NewsLetterJpaEntity> findLatestNewsLetter();

}
