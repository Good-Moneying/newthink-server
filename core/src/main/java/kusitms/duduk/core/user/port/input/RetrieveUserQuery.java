package kusitms.duduk.core.user.port.input;

import kusitms.duduk.core.user.dto.response.RetriveHomeResponse;

public interface RetrieveUserQuery {
    boolean isUserRegisteredByEmail(String email);

    RetriveHomeResponse home(String email);
}
