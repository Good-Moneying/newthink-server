package kusitms.duduk.apiserver.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.duduk.core.exception.ErrorResponse;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserEmailRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserNicknameRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "유저 API")
public interface UserControllerDocs {
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "회원 가입", description = "회원 가입을 진행합니다.")
    ResponseEntity<UserResponse> register(
        @Parameter(description = "유저 생성 DTO ", required = true)
        @RequestBody final CreateUserRequest request
    );

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "유저 검증", description = "해당 이메일로 가입한 유저가 있는지 검증합니다.")
    ResponseEntity<Void> validateEmail(
        @Parameter(description = "유저 검증 DTO ", required = true)
        @RequestBody final ValidateUserEmailRequest request
    );

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "유저 검증", description = "해당 닉네임으로 가입한 유저가 있는지 검증합니다.")
    ResponseEntity<Void> validateNickname(
        @Parameter(description = "유저 검증 DTO ", required = true)
        @RequestBody final ValidateUserNicknameRequest request
    );
}
