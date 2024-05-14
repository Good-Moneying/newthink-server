package kusitms.duduk.batch.config;

import kusitms.duduk.batch.dto.crawling.CrawlingNews;
import kusitms.duduk.batch.dto.openai.parsing.ParsedAiContent;
import kusitms.duduk.batch.util.CrawlingUtils;
import kusitms.duduk.batch.util.GenerateAiNewsUtils;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class NewsLetterStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

//    private final CrawlingReader crawlingReader;
//    private final CrawlingProcessor crawlingProcessor;
//    private final CrawlingWriter crawlingWriter;
    private final CrawlingUtils crawlingUtils;
    private final GenerateAiNewsUtils generateAiNewsUtils;
    private final CreateNewsLetterUseCase createNewsLetterUseCase;

    @Value("${crawling.target-url}")
    private String TARGET_URL;

    @Bean
    public Step crawlingNewsStep() {
        return new StepBuilder("crawlingNewsStep", jobRepository)
//                .<CrawlingNews, CreateNewsLetterRequest>chunk(1,transactionManager)
//                .reader(crawlingReader)
//                .processor(crawlingProcessor)
//                .writer(crawlingWriter)
                .tasklet(
                        (contribution, chunkContext) -> {
                            CrawlingNews crawlingNews = crawlingUtils.getCrawlingNews(TARGET_URL);
                            ParsedAiContent parsedAiContent = generateAiNewsUtils.getAIResponse(crawlingNews);

                            createNewsLetterUseCase.create(
                                    new CreateNewsLetterRequest(
                                            crawlingNews.getThumbnailURL(),
                                            parsedAiContent.getHeadline(),
                                            parsedAiContent.getContent(),
                                            parsedAiContent.getKeywords(),
                                            parsedAiContent.getCategory(),
                                            "미정",
                                            "AI"
                                    )
                            );
                            return RepeatStatus.FINISHED;
                        }, transactionManager
                )
                .allowStartIfComplete(true)
                .build();
    }
}
