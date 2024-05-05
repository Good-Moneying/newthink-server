package kusitms.duduk.core.user.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserResponse(
    Long id, String email, String nickname
) {

}
