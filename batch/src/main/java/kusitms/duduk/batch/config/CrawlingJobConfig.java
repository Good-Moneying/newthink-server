package kusitms.duduk.batch.config;


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
    private final Step stepConfig;

    @Bean(name = "generateNewsLetterJob")
    public Job generateNewsLetterJob() {
        return new JobBuilder("generateNewsLetterJob", jobRepository)
                .start(stepConfig)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
