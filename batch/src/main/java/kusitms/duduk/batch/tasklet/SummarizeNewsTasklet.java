package kusitms.duduk.batch.tasklet;

import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.core.crawling.dto.response.CrawlingNewsResponse;
import kusitms.duduk.core.openai.dto.request.OpenAiSummaryNewsLetterRequest;
import kusitms.duduk.core.openai.dto.response.OpenAiSummaryNewsLetterResponse;
import kusitms.duduk.core.openai.port.output.OpenAiClientPort;
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
public class SummarizeNewsTasklet implements Tasklet {

    private final OpenAiClientPort<OpenAiSummaryNewsLetterRequest, OpenAiSummaryNewsLetterResponse> openAiClientPort;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        List<CrawlingNewsResponse> crawlingNewsResponses = (List<CrawlingNewsResponse>) chunkContext
            .getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().get("crawlingNewsResponses");

        List<OpenAiSummaryNewsLetterResponse> aiResponses = crawlingNewsResponses.stream()
            .map(crawlingNews -> openAiClientPort.chat(new OpenAiSummaryNewsLetterRequest(crawlingNews.getContent())))
            .collect(Collectors.toList());

        chunkContext.getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().put("aiResponses", aiResponses);
        log.info("Parsed content for {} news articles", aiResponses.size());
        return RepeatStatus.FINISHED;
    }
}