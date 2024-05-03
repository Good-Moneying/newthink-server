package kusitms.duduk.core.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateUserRequest(@NotNull String email,
		@NotNull String nickname,
		@NotNull String gender,
		@JsonFormat(pattern = "yyyyMMdd") @NotNull LocalDate birthDay,
		@NotNull String category) {

}
