package kusitms.duduk.apiserver.security.application;

import java.util.Map;
import kusitms.duduk.apiserver.security.handler.OAuthHandler;
import kusitms.duduk.apiserver.security.presentation.dto.OAuthDetailResponse;
import kusitms.duduk.apiserver.security.presentation.dto.OAuthLoginResponse;
import kusitms.duduk.domain.security.domain.Provider;
import kusitms.duduk.domain.security.application.JwtTokenProvider;
import kusitms.duduk.domain.security.jwt.JwtTokenInfo;
import kusitms.duduk.domain.user.application.port.in.RetrieveUserQuery;
import kusitms.duduk.domain.user.application.port.in.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthApplicationService {

    private final Map<Provider, OAuthHandler> oAuthHandlers;
    private final RetrieveUserQuery retrieveUserQuery;
    private final UpdateUserUseCase updateUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    public OAuthLoginResponse login(Provider provider, String accessToken) {
        OAuthDetailResponse oAuthDetailResponse = fetchOAuthDetails(provider, accessToken);

        JwtTokenInfo jwtTokenInfo = jwtTokenProvider.createTokenInfo(oAuthDetailResponse.email());

        boolean isRegistered = checkUserRegistration(oAuthDetailResponse.email());
        updateRefreshTokenIfNeeded(isRegistered, oAuthDetailResponse.email(), jwtTokenInfo.refreshToken());

        return new OAuthLoginResponse(
            jwtTokenInfo.accessToken(),
            jwtTokenInfo.refreshToken(),
            provider,
            isRegistered);
    }

    private OAuthDetailResponse fetchOAuthDetails(Provider provider, String accessToken) {
        OAuthHandler oAuthHandler = oAuthHandlers.get(provider);
        return oAuthHandler.retrieveOAuthDetail(accessToken);
    }

    private boolean checkUserRegistration(String email) {
        return retrieveUserQuery.isUserRegisteredByEmail(email);
    }

    private void updateRefreshTokenIfNeeded(boolean isRegistered, String email, String refreshToken) {
        if (isRegistered) {
            updateUserUseCase.updateRefreshToken(email, refreshToken);
        }
    }
}