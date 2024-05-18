package kusitms.duduk.core.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateUserRequest(
    @Schema(description = "사용자의 이메일 주소", example = "good.moneying.2024@gmail.com", required = true)
    @NotNull String email,

    @Schema(description = "사용자의 닉네임", example = "백꾸준", required = true)
    @NotNull String nickname,

    @Schema(description = "OAuth2 리프레쉬 토큰", required = true)
    @NotNull String refreshToken,

    @Schema(description = "사용자의 성별 ex) male, female", example = "male", required = true)
    @NotNull String gender,

    @Schema(description = "사용자의 생년월일, 형식은 YYYYMMDD", example = "20000101", required = true)
    @JsonFormat(pattern = "yyyyMMdd")
    @NotNull LocalDate birthDay,

    @Schema(description = "인증 제공자 ex) google, kakao, naver", example = "kakao", required = true)
    @NotNull String provider,

    @Schema(description = "사용자 관심사 ex) stock, fund, crypto, estate", example = "finance", required = true)
    @NotNull String category,

    @Schema(description = "사용자의 앱 사용 목표 ex) everyday, one_to_two, three_to_four", example = "everyday", required = true)
    @NotNull String goal
) {
}
