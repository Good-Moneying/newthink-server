package kusitms.duduk.core.user.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserResponse(String email, String nickname
) {

}
