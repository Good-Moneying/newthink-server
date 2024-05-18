package kusitms.duduk.core.ai.port.output;

import kusitms.duduk.core.ai.dto.response.OpenAIResponse;
import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;

public interface AiClientPort {
    OpenAIResponse retrieveAiResponse(CrawlingNewsResponse crawlingNews);
}
