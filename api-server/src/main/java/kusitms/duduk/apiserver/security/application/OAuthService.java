package kusitms.duduk.apiserver.security.application;

import java.util.Map;
import kusitms.duduk.apiserver.security.handler.OAuthHandler;
import kusitms.duduk.apiserver.security.presentation.dto.OAuthDetailResponse;
import kusitms.duduk.apiserver.security.presentation.dto.OAuthLoginResponse;
import kusitms.duduk.apiserver.user.application.UserQueryService;
import kusitms.duduk.domain.security.domain.Provider;
import kusitms.duduk.domain.security.application.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthService {

    private final Map<Provider, OAuthHandler> oAuthHandlers;
    private final UserQueryService userQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    public OAuthLoginResponse login(Provider provider, String accessToken) {
        log.info("login() start");
        OAuthHandler oAuthHandler = oAuthHandlers.get(provider);
        OAuthDetailResponse oAuthDetailResponse = oAuthHandler.retrieveOAuthDetail(accessToken);

        boolean isRegistered = userQueryService.isUserRegisteredByEmail(
            oAuthDetailResponse.email());

        return new OAuthLoginResponse(
            jwtTokenProvider.createAccessToken(oAuthDetailResponse.email()),
            provider,
            isRegistered);
    }
}
