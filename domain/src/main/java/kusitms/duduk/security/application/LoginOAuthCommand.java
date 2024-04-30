package kusitms.duduk.security.application;

import kusitms.duduk.security.application.port.in.LoginOAuthUseCase;
import kusitms.duduk.security.domain.JwtTokenProvider;
import kusitms.duduk.security.domain.Provider;
import kusitms.duduk.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.security.dto.response.OAuthLoginResponse;
import kusitms.duduk.security.dto.response.JwtTokenResponse;
import kusitms.duduk.user.application.port.in.RetrieveUserQuery;
import kusitms.duduk.user.application.port.in.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginOAuthCommand implements LoginOAuthUseCase {

    private final RetrieveUserQuery retrieveUserQuery;
    private final UpdateUserUseCase updateUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    public OAuthLoginResponse process(OAuthDetailResponse response, Provider provider) {

        JwtTokenResponse jwtTokenInfo = jwtTokenProvider.createTokenInfo(response.email());

        boolean isRegistered = retrieveUserQuery.isUserRegisteredByEmail(response.email());
        updateRefreshTokenIfRegistered(isRegistered, response.email(), jwtTokenInfo.refreshToken());

        return new OAuthLoginResponse(
            jwtTokenInfo.accessToken(),
            jwtTokenInfo.refreshToken(),
            provider,
            isRegistered);
    }

    private void updateRefreshTokenIfRegistered(boolean isRegistered, String email, String refreshToken) {
        if (isRegistered) {
            updateUserUseCase.updateRefreshToken(email, refreshToken);
        }
    }
}
