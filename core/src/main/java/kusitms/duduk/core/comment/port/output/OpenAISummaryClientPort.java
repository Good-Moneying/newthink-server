package kusitms.duduk.core.comment.port.output;

import kusitms.duduk.core.comment.dto.request.OpenAISummaryRequest;
import kusitms.duduk.core.comment.dto.response.OpenAISummaryResponse;

public interface OpenAISummaryClientPort {

    OpenAISummaryResponse create(OpenAISummaryRequest request);

}
