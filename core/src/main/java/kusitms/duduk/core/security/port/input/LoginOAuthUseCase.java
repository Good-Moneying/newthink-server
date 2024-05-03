package kusitms.duduk.core.security.port.input;


import kusitms.duduk.core.security.dto.response.OAuthLoginResponse;
import kusitms.duduk.domain.user.Provider;

public interface LoginOAuthUseCase {
    OAuthLoginResponse process(Provider provider, String accessToken);
}
