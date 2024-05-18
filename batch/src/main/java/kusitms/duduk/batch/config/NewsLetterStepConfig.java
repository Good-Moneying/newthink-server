package kusitms.duduk.batch.config;

import kusitms.duduk.application.ai.service.ParsingAiContent;
import kusitms.duduk.batch.crawling.service.NewsCrawler;
import kusitms.duduk.core.ai.dto.response.OpenAIResponse;
import kusitms.duduk.core.ai.dto.response.ParsedAiContentResponse;
import kusitms.duduk.core.ai.port.output.AiClientPort;
import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.core.s3.port.input.S3FileUploadPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class NewsLetterStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final List<NewsCrawler> newsCrawlerList;
    private final AiClientPort aiClientPort;
    private final ParsingAiContent parsingAiContent;
    private final CreateNewsLetterUseCase createNewsLetterUseCase;
    private final S3FileUploadPort s3FileUploadPort;

    @Bean
    public Step crawlingNewsStep() {
        return new StepBuilder("crawlingNewsStep", jobRepository)
                .tasklet(
                        (contribution, chunkContext) -> {
                            //커멘드 패턴을 활용한 다형성 구현 방식 적용
                            log.debug("crawlingNewsStep 실행");

                            for(NewsCrawler newsCrawler : newsCrawlerList) {
                                CrawlingNewsResponse crawlingNews = newsCrawler.crawl();
                                String imageUrl = s3FileUploadPort.uploadFile(crawlingNews.getThumbnailURL());

                                OpenAIResponse openAIResponse = aiClientPort.retrieveAiResponse(crawlingNews);
                                log.debug("openAIResponse: {}", openAIResponse.getContent());

                                ParsedAiContentResponse parsedAiContent = parsingAiContent.getParsingResult(openAIResponse.getContent());
                                log.debug("parsedAiContent: {}", parsedAiContent.toString());

                                createNewsLetterUseCase.create(
                                        new CreateNewsLetterRequest(
                                                imageUrl,
                                                parsedAiContent.title(),
                                                parsedAiContent.content(),
                                                parsedAiContent.keywords(),
                                                parsedAiContent.category(),
                                                "미정",
                                                "AI"
                                        )
                                );
                            }
                            return RepeatStatus.FINISHED;
                        }, transactionManager
                )
                .allowStartIfComplete(true)
                .build();
    }
}
