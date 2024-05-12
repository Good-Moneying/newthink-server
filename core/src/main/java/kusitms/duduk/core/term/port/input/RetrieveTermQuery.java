package kusitms.duduk.core.term.port.input;

import kusitms.duduk.core.term.dto.request.RetrieveTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.domain.user.User;

public interface RetrieveTermQuery {
    RetrieveTermResponse retrieve(RetrieveTermRequest request);
    RetrieveTermResponse retrieveLatestTerm(User user);
}
