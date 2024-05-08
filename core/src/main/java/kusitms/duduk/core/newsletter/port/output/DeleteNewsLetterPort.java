package kusitms.duduk.core.newsletter.port.output;

import kusitms.duduk.domain.global.Id;

public interface DeleteNewsLetterPort {

    void deleteById(Long id);

    void deleteAll();
}
