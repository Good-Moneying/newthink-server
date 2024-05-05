package kusitms.duduk.application.security.service;

import java.util.Map;

import kusitms.duduk.core.security.dto.response.JwtTokenResponse;
import kusitms.duduk.core.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.core.security.dto.response.OAuthLoginResponse;
import kusitms.duduk.core.security.port.input.LoginOAuthUseCase;
import kusitms.duduk.core.security.port.output.OAuthClientPort;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.core.user.port.input.RetrieveUserQuery;
import kusitms.duduk.core.user.port.input.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginOAuthCommand implements LoginOAuthUseCase {

    private final RetrieveUserQuery retrieveUserQuery;
    private final UpdateUserUseCase updateUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;
    private final Map<Provider, OAuthClientPort> oAuthClientPortMap;

    public OAuthLoginResponse process(Provider provider, String accessToken) {
        OAuthDetailResponse response = oAuthClientPortMap.get(provider).retrieveOAuthDetail(accessToken);
        JwtTokenResponse jwtTokenInfo = jwtTokenProvider.createTokenInfo(response.email());

        boolean isRegistered = retrieveUserQuery.isUserRegisteredByEmail(response.email());
        updateRefreshTokenIfRegistered(isRegistered, response.email(), jwtTokenInfo.refreshToken());

        return new OAuthLoginResponse(
            jwtTokenInfo.accessToken(),
            jwtTokenInfo.refreshToken(),
            provider,
            isRegistered);
    }

    private void updateRefreshTokenIfRegistered(boolean isRegistered, String email,
        String refreshToken) {
        if (isRegistered) {
            updateUserUseCase.updateRefreshToken(email, refreshToken);
        }
    }
}
