package kusitms.duduk.apiserver.healthcheck.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kusitms.duduk.common.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface HealthCheckControllerDocs {

    @GetMapping("/api/healthcheck")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "서버 상태 정상"),
        @ApiResponse(responseCode = "400", description = "서버 상태 비정상",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "헬스 체크", description = "서버 상태 확인")
    ResponseEntity<Void> healthCheck();
}
