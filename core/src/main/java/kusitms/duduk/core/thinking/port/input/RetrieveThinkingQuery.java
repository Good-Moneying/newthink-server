package kusitms.duduk.core.thinking.port.input;

import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingDetailResponse;
import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingHomeResponse;

public interface RetrieveThinkingQuery {

    RetrieveThinkingHomeResponse retrieveThinkingHome(final String email);

    RetrieveThinkingDetailResponse retrieveThinkingDetail(final Long thinkingId);
}
