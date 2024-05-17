package kusitms.duduk.apiserver.thinking.presentation;

import java.util.List;
import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.comment.dto.request.OpenAISummaryRequest;
import kusitms.duduk.core.comment.dto.response.OpenAISummaryResponse;
import kusitms.duduk.core.comment.port.output.OpenAISummaryClientPort;
import kusitms.duduk.core.thinking.port.input.RetrieveThinkingQuery;
import kusitms.duduk.domain.thinking.Thinking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/thinkings")
public class ThinkingController implements ThinkingControllerDocs{

    private final RetrieveThinkingQuery retrieveThinkingQuery;
    private final OpenAISummaryClientPort openAISummaryClientPort;

    // todo : 후에 RetrieveThinkingHomeResponse로 고칠 예정
    @GetMapping("/home")
    public ResponseEntity<List<Thinking>> retrieveThinkingHome(
        @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        return ResponseEntity.ok(retrieveThinkingQuery.retrieveThinkingHome(customUserDetails.getEmail()));
    }

    @GetMapping
    public ResponseEntity<Void> retrieveThinkingDetail() {
        // todo : 생각 구름 조회 로직
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/summary")
    public ResponseEntity<OpenAISummaryResponse> summaryThinking(
        @RequestBody OpenAISummaryRequest request
    ) {
        return ResponseEntity.ok(openAISummaryClientPort.create(request));
    }
    @PostMapping
    public ResponseEntity<Void> createThinkingCloud() {
        // todo : 생각 구름 생성 로직
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
