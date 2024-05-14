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
@EnableBatchProcessing      //스프링 배치가 제공하는 어노테이션으로, 배치 인프라스트럭처를 위한 대부분의 스프링 빈 정의를 제공한다. ex - JopRepository, JobLauncher 등의 컴포넌트를 명시적으로 포함시킬 필요 x
@Configuration
@RequiredArgsConstructor
public class NewsLetterJobConfig {

    //    job의 step
    //    1. 뉴스를 크롤링 힌다 (한국경제 가장 상단의 뉴스)
    //    2. openAI api를 활용해서 헤드라인, 뉴스레터, 키워드, 카테고리를 생성한다.
    //    3. 생성한 정보들을 DB에 저장한다.

    private final JobRepository jobRepository;
    private final Step stepConfig;

    @Bean
    public Job generateNewsLetter(){
        return new JobBuilder("generateNewsLetterJob",jobRepository)
                .start(stepConfig)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
