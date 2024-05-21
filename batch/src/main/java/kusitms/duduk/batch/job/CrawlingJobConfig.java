package kusitms.duduk.batch.job;

import kusitms.duduk.batch.step.CrawlingStepConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class CrawlingJobConfig {

    private final JobRepository jobRepository;
    private final CrawlingStepConfig stepConfig;

    @Bean(name = "generateNewsLetterJob")
    public Job generateNewsLetterJob() {
        return new JobBuilder("generateNewsLetterJob", jobRepository)
                .start(stepConfig.crawlingNewsStep())
                .next(stepConfig.UploadImageStep())
                .next(stepConfig.SummarizeNewsStep())
                .next(stepConfig.CreateNewsStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }
}

