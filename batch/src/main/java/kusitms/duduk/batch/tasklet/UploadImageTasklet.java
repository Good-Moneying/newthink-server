package kusitms.duduk.batch.tasklet;

import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.core.crawling.dto.response.CrawlingNewsResponse;
import kusitms.duduk.core.s3.port.input.S3FileUploadPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadImageTasklet implements Tasklet {

    private final S3FileUploadPort s3FileUploadPort;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        List<CrawlingNewsResponse> crawlingNewsResponses = (List<CrawlingNewsResponse>) chunkContext
            .getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().get("crawlingNewsResponses");

        List<String> imageUrls = crawlingNewsResponses.stream()
            .map(crawlingNews -> s3FileUploadPort.uploadFile(crawlingNews.getThumbnailURL()))
            .collect(Collectors.toList());

        chunkContext.getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().put("imageUrls", imageUrls);
        log.info("Uploaded {} images to S3", imageUrls.size());
        return RepeatStatus.FINISHED;
    }
}