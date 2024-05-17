package kusitms.duduk.core.thinking.port.input;

import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.domain.thinking.Thinking;

public interface CreateThinkingUseCase {

    Thinking create(CreateThinkingRequest request);
}
