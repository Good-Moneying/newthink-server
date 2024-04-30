package kusitms.duduk.security.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kusitms.duduk.security.dto.response.OAuthLoginResponse;
import kusitms.duduk.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public interface OAuthControllerDocs {
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "OAuth2 로그인", description = "소셜 로그인 플랫폼을 통한 로그인을 수행합니다.")
    ResponseEntity<OAuthLoginResponse> oAuthLogin(
        @Parameter(description = "소셜 로그인 플랫폼", required = true)
        @PathVariable final String provider,
        @Parameter(description = "카카오 리소스 서버에 접근 가능한 Access Token. 헤더로 전달", required = true)
        @RequestHeader("Authorization") final String accessToken
    );

}
