package kusitms.duduk.core.user.dto.request;

import jakarta.validation.constraints.NotNull;

public record ValidateUserNicknameRequest(@NotNull String nickname) {

}
