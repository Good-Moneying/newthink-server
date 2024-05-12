package kusitms.duduk.core.term.port.input;

import kusitms.duduk.core.term.dto.request.RetrieveTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;

public interface RetrieveTermUseCase {
    RetrieveTermResponse retrieve(RetrieveTermRequest request);
}
