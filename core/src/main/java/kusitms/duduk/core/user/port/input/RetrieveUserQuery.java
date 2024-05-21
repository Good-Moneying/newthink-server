package kusitms.duduk.core.user.port.input;

import kusitms.duduk.core.user.dto.response.RetrieveHomeResponse;
import kusitms.duduk.core.user.dto.response.RetrieveMyPageResponse;

public interface RetrieveUserQuery {
    boolean isUserRegisteredByEmail(String email);
    String retrieveUserNicknameByEmail(String email);

    RetrieveHomeResponse home(String email);

    RetrieveMyPageResponse mypage(String email);
}
