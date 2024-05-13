package kusitms.duduk.batch.processor;

import kusitms.duduk.batch.dto.crawling.CrawlingNews;
import kusitms.duduk.batch.dto.openai.parsing.ParsedAiContent;
import kusitms.duduk.batch.util.GenerateAiNewsUtils;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CrawlingProcessor implements ItemProcessor<CrawlingNews, CreateNewsLetterRequest> {

    private final GenerateAiNewsUtils generateAiNewsUtils;

    @Override
    public CreateNewsLetterRequest process(CrawlingNews item) throws Exception {
        ParsedAiContent parsedAiContent = generateAiNewsUtils.getAIResponse(item);
        //S3
        return CreateNewsLetterRequest.
            builder()
                .thumbnail(null)
                .title(parsedAiContent.getHeadline())
                .content(parsedAiContent.getContent())
                .keywords(null)
                .category(null)
            .build();
    }
}
