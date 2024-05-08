package kusitms.duduk.core.newsletter.port.input;

import kusitms.duduk.core.newsletter.dto.request.CreateAiNewsLetterRequest;
import kusitms.duduk.domain.newsletter.NewsLetter;

public interface CreateNewsLetterUseCase {

    NewsLetter create(CreateAiNewsLetterRequest request);
}