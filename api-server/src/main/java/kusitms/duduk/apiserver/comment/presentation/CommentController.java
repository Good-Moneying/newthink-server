package kusitms.duduk.apiserver.comment.presentation;

import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import kusitms.duduk.core.comment.port.input.CreateCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController implements CommentControllerDocs {

    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping("/{newsLetterId}")
    public ResponseEntity<CommentResponse> create(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable(name = "newsLetterId") final Long newsLetterId,
        @RequestBody CreateCommentRequest request
    ) {
        return new ResponseEntity<>(
            createCommentUseCase.create(userDetails.getEmail(), newsLetterId, request),
            HttpStatus.CREATED);
    }
}
