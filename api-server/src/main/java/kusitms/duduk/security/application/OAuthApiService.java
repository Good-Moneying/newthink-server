package kusitms.duduk.security.application;

import java.util.Map;
import kusitms.duduk.security.application.port.in.LoginOAuthUseCase;
import kusitms.duduk.security.handler.OAuthHandler;
import kusitms.duduk.security.dto.response.OAuthLoginResponse;
import kusitms.duduk.security.domain.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthApiService {

    private final Map<Provider, OAuthHandler> oAuthHandlers;
    private final LoginOAuthUseCase loginOAuthUseCase;

    public OAuthLoginResponse login(Provider provider, String accessToken) {
        OAuthHandler oAuthHandler = oAuthHandlers.get(provider);
        return loginOAuthUseCase.process(oAuthHandler.retrieveOAuthDetail(accessToken), provider);
    }
}
