package kusitms.duduk.batch.processor;

import kusitms.duduk.batch.dto.crawling.CrawlingNews;
import kusitms.duduk.batch.dto.openai.parsing.ParsedAiContent;
import kusitms.duduk.batch.util.GenerateAiNewsUtils;
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
public class CrawlingProcessor implements ItemProcessor<CrawlingNews, NewsLetter> {

    private final GenerateAiNewsUtils generateAiNewsUtils;

    @Override
    public NewsLetter process(CrawlingNews item) throws Exception {
        ParsedAiContent parsedAiContent = generateAiNewsUtils.getAIResponse(item);

        return NewsLetter.builder()
                .editorId(Id.of(0L))
                .thumbnail(null)
                .title(Title.from(parsedAiContent.getHeadline()))
                .content(Content.from(parsedAiContent.getContent()))
                .keywords(null)
                .category(null)
                .type(null)
                .summary(null)
                .viewCount(Count.from(0))
                .scrapCount(Count.from(0))
                .build();
    }
}
