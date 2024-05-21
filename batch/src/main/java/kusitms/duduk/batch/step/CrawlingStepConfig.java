package kusitms.duduk.batch.step;

import kusitms.duduk.batch.tasklet.CrawlingNewsTasklet;
import kusitms.duduk.batch.tasklet.CreateNewsLetterTasklet;
import kusitms.duduk.batch.tasklet.SummarizeNewsTasklet;
import kusitms.duduk.batch.tasklet.UploadImageTasklet;
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
public class CrawlingStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CrawlingNewsTasklet crawlingNewsTasklet;
    private final UploadImageTasklet uploadImageTasklet;
    private final SummarizeNewsTasklet parseNewsContentTasklet;
    private final CreateNewsLetterTasklet createNewsletterTasklet;

    @Bean
    public Step crawlingNewsStep() {
        return new StepBuilder("crawlingNewsStep", jobRepository)
            .tasklet(crawlingNewsTasklet, transactionManager)
            .tasklet(uploadImageTasklet, transactionManager)
            .tasklet(parseNewsContentTasklet, transactionManager)
            .tasklet(createNewsletterTasklet, transactionManager)
            .allowStartIfComplete(true)
            .build();
    }
}