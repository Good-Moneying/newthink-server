package kusitms.duduk.core.user.dto.request;

import jakarta.validation.constraints.NotNull;

public record ValidateUserEmailRequest(@NotNull String email) {

}
