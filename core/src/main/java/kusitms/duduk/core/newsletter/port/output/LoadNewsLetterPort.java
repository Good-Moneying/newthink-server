package kusitms.duduk.core.newsletter.port.output;

import java.util.List;
import java.util.Optional;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.newsletter.NewsLetter;

public interface LoadNewsLetterPort {

    Optional<NewsLetter> findById(Long id);

    Optional<NewsLetter> findLatestNewsLetter();

    List<NewsLetter> findAll();

    List<NewsLetter> findRealtimeTrendNewsLetters();

    List<NewsLetter> findCustomizeNewsLetters(Category category);
}
