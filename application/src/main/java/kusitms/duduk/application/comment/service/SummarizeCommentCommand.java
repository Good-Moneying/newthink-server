package kusitms.duduk.application.comment.service;

import kusitms.duduk.core.comment.dto.request.OpenAISummaryRequest;
import kusitms.duduk.core.comment.port.input.SummarizeCommentUseCase;
import kusitms.duduk.core.openai.port.output.OpenAISummaryClientPort;
import kusitms.duduk.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SummarizeCommentCommand implements SummarizeCommentUseCase {

    private final OpenAISummaryClientPort summaryClientPort;

    @Override
    public String summarize(Comment comment) {
        OpenAISummaryRequest request = createSummaryRequest(comment);
        return summaryClientPort.summarize(request);
    }

    private OpenAISummaryRequest createSummaryRequest(Comment comment) {
        return OpenAISummaryRequest.builder()
            .comment(comment.getSentence().getValue())
            .build();
    }
}
