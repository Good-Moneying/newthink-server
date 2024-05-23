package kusitms.duduk.core.newsletter.port.input;

import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;

public interface CreateNewsLetterUseCase {

    NewsLetterResponse create(CreateNewsLetterRequest request);

    NewsLetterResponse createByEditor(CreateNewsLetterRequest request, String email);
}