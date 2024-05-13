package kusitms.duduk.batch;

import kusitms.duduk.batch.config.NewsLetterJobConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
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
public class NewsLetterBatchService {

    private final JobLauncher jobLauncher;

    @Qualifier("generateNewsLetter")
    private final Job generateNewsLetter;

    //@Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 */1 * * *")
    public void scheduledTask() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("News letter batch service started");

        jobLauncher.run(generateNewsLetter,new JobParametersBuilder().toJobParameters());
    }
}
