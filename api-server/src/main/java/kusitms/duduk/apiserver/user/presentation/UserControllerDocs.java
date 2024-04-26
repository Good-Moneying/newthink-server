package kusitms.duduk.apiserver.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kusitms.duduk.apiserver.user.presentation.dto.OAuthResponse;
import kusitms.duduk.core.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;

public interface UserControllerDocs {

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "SUCCESS"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "OAuth2 로그인")
    ResponseEntity<OAuthResponse> oAuthLogin();
}
