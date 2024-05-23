package kusitms.duduk.application.comment.service;

import kusitms.duduk.core.openai.dto.request.OpenAiSummaryCommentRequest;
import kusitms.duduk.core.comment.port.input.SummarizeCommentUseCase;
import kusitms.duduk.core.openai.port.output.OpenAiClientPort;
import kusitms.duduk.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SummarizeCommentCommand implements SummarizeCommentUseCase {

    private final OpenAiClientPort<OpenAiSummaryCommentRequest, String> openAiClientPort;

    @Override
    public String summarize(Comment comment) {
        OpenAiSummaryCommentRequest request = new OpenAiSummaryCommentRequest(
            comment.getSentence().getValue());
        log.info("Open AI 의해 요약된 문장 : {}", request.toString());
        return openAiClientPort.chat(request);
    }
}
