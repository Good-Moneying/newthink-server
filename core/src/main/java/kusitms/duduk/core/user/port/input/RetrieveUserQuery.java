package kusitms.duduk.core.user.port.input;

import kusitms.duduk.core.user.dto.response.RetrieveHomeResponse;

public interface RetrieveUserQuery {
    boolean isUserRegisteredByEmail(String email);

    RetrieveHomeResponse home(String email);
}
