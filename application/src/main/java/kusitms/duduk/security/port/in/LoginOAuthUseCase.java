package kusitms.duduk.security.port.in;

import kusitms.duduk.user.Provider;
import kusitms.duduk.security.dto.response.OAuthLoginResponse;

public interface LoginOAuthUseCase {
    OAuthLoginResponse process(Provider provider, String accessToken);
}
