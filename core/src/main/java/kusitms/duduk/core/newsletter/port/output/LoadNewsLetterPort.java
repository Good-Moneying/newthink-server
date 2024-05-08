package kusitms.duduk.core.newsletter.port.output;

import java.util.List;
import java.util.Optional;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;

public interface LoadNewsLetterPort {
    Optional<NewsLetter> findById(Long id);

    List<NewsLetter> findAll();
}
