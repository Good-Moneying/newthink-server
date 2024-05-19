package kusitms.duduk.core.user.dto.response;

import java.util.List;
import lombok.Builder;

@Builder(toBuilder = true)
public record UserResponse(Long userId, String email, String nickname, List<Long> archives
) {

}
