package kusitms.duduk.batch.config;

import kusitms.duduk.batch.dto.crawling.CrawlingNews;
import kusitms.duduk.batch.processor.CrawlingProcessor;
import kusitms.duduk.batch.reader.CrawlingReader;
import kusitms.duduk.batch.writer.CrawlingWriter;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
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

    private final CrawlingReader crawlingReader;
    private final CrawlingProcessor crawlingProcessor;
    private final CrawlingWriter crawlingWriter;

    @Bean
    public Step crawlingNewsStep() {
        return new StepBuilder("crawlingNewsStep", jobRepository)
                .<CrawlingNews, NewsLetter>chunk(10,transactionManager)
                .reader(crawlingReader)
                .processor(crawlingProcessor)
                .writer(crawlingWriter)
                .build();
    }
}
