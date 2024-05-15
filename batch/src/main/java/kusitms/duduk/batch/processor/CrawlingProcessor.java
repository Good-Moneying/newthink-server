package kusitms.duduk.batch.processor;

import kusitms.duduk.application.ai.service.ParsingAiContent;
import kusitms.duduk.core.ai.dto.response.OpenAIResponse;
import kusitms.duduk.core.ai.port.output.AiClientPort;
import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;
import kusitms.duduk.core.ai.dto.response.ParsedAiContentResponse;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CrawlingProcessor implements ItemProcessor<CrawlingNewsResponse, CreateNewsLetterRequest> {

    private final AiClientPort aiClientPort;
    private final ParsingAiContent parsingAiContent;

    @Override
    public CreateNewsLetterRequest process(CrawlingNewsResponse item) throws Exception {
        OpenAIResponse openAIResponse = aiClientPort.retrieveAiResponse(item);
        ParsedAiContentResponse parsedAiContentResponse = parsingAiContent.getParsingResult(openAIResponse.getContent());

        return new CreateNewsLetterRequest(
                item.getThumbnailURL(),
                parsedAiContentResponse.headline(),
                parsedAiContentResponse.content(),
                parsedAiContentResponse.keywords(),
                parsedAiContentResponse.category(),
                null,
                "AI"
        );
    }
}
