package kusitms.duduk.core.term.port.input;

import kusitms.duduk.core.term.dto.request.CreateTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;

public interface CreateTermUseCase {

    RetrieveTermResponse create(CreateTermRequest request);
}
