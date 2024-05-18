package kusitms.duduk.application.global.api;

import kusitms.duduk.core.comment.dto.request.OpenAISummaryRequest;
import kusitms.duduk.core.comment.dto.response.OpenAISummaryResponse;
import kusitms.duduk.core.comment.port.output.OpenAISummaryClientPort;
import org.springframework.stereotype.Component;

@Component
public class OpenAISummaryApiClient implements OpenAISummaryClientPort {

    @Override
    public OpenAISummaryResponse create(OpenAISummaryRequest request) {
        return new OpenAISummaryResponse("임시 문장");
    }
}
