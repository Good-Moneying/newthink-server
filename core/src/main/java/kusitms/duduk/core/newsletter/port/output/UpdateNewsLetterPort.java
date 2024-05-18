package kusitms.duduk.core.newsletter.port.output;

import java.util.Optional;
import kusitms.duduk.domain.newsletter.NewsLetter;

public interface UpdateNewsLetterPort {
    Optional<NewsLetter> update(NewsLetter newsLetter);
}
