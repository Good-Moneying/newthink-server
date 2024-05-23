package kusitms.duduk.batch;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BatchScheduler {

    private final JobLauncher jobLauncher;

    @Qualifier("generateNewsLetterJob")
    private final Job generateNewsLetterJob;

    //@Scheduled(fixedRate = 5000)2
//    @Scheduled(cron = "1 * * * * *")
    @Scheduled(cron = "0 1 * * * *")
    public void scheduledTask()
        throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("뉴스레터 배치 애플리케이션을 시작 : {}", LocalDateTime.now());

        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();

        jobLauncher.run(generateNewsLetterJob, jobParameters);
    }
}
