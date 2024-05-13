package kusitms.duduk.core.user.port.input;

import kusitms.duduk.core.user.dto.response.RetrieveHomeResponse;
import kusitms.duduk.core.user.dto.response.RetrieveMypageResponse;
import kusitms.duduk.core.user.dto.response.UserResponse;

public interface RetrieveUserQuery {
    boolean isUserRegisteredByEmail(String email);

    RetrieveHomeResponse home(String email);

    RetrieveMypageResponse mypage(String email);
}
