package kusitms.duduk.apiserver.comment.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.common.exception.ErrorResponse;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentControllerDocs {

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "코멘트 작성", description = "Access Token에서 추출한 유저 식별자와 뉴스레터 아이디를 통해 코멘트를 작성합니다.")
    ResponseEntity<CommentResponse> create(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable(name = "newsLetterId") final Long newsLetterId,
        @RequestBody CreateCommentRequest request
    );
}
