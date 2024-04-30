package kusitms.duduk.security.application.port.in;

import kusitms.duduk.security.domain.Provider;
import kusitms.duduk.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.security.dto.response.OAuthLoginResponse;

public interface LoginOAuthUseCase {
    OAuthLoginResponse process(OAuthDetailResponse response, Provider provider);
}
