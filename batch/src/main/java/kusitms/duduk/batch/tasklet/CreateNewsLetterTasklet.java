package kusitms.duduk.batch.tasklet;

import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.core.openai.dto.response.OpenAiSummaryNewsLetterResponse;
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
public class CreateNewsLetterTasklet implements Tasklet {

    private final CreateNewsLetterUseCase createNewsLetterUseCase;
    private static final String TYPE_AI = "AI";

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        List<String> imageUrls = (List<String>) chunkContext.getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().get("imageUrls");
        List<OpenAiSummaryNewsLetterResponse> aiResponses = (List<OpenAiSummaryNewsLetterResponse>) chunkContext
            .getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().get("aiResponses");

        for (int i = 0; i < aiResponses.size(); i++) {
            OpenAiSummaryNewsLetterResponse aiResponse = aiResponses.get(i);
            String imageUrl = imageUrls.get(i);

            createNewsLetterUseCase.create(
	new CreateNewsLetterRequest(
	    imageUrl,
	    aiResponse.getContent(),
	    aiResponse.getContent(),
	    aiResponse.getKeywords(),
	    aiResponse.getCategory(),
	    null,
	    TYPE_AI
	)
            );
        }
        log.info("Created {} newsletters", aiResponses.size());
        return RepeatStatus.FINISHED;
    }
}