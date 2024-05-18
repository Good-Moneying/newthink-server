package kusitms.duduk.core.openai.port.output;

import kusitms.duduk.core.comment.dto.request.OpenAISummaryRequest;

public interface OpenAISummaryClientPort {

    String summarize(OpenAISummaryRequest request);

}
