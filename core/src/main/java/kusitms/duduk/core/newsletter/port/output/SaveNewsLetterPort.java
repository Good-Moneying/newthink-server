package kusitms.duduk.core.newsletter.port.output;

import kusitms.duduk.domain.newsletter.NewsLetter;

public interface SaveNewsLetterPort {
    NewsLetter create(NewsLetter newsLetter);
    void saveAndFlush(NewsLetter newsLetter);
}
