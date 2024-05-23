package kusitms.duduk.application.security.service;

import java.util.Map;

import kusitms.duduk.application.user.event.LoginUserEvent;
import kusitms.duduk.core.security.dto.response.JwtTokenResponse;
import kusitms.duduk.core.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.core.security.dto.response.OAuthLoginResponse;
import kusitms.duduk.core.security.port.input.LoginOAuthUseCase;
import kusitms.duduk.core.security.port.output.OAuthClientPort;
import kusitms.duduk.domain.user.User;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.core.user.port.input.RetrieveUserQuery;
import kusitms.duduk.core.user.port.input.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginOAuthCommand implements LoginOAuthUseCase {

    private final RetrieveUserQuery retrieveUserQuery;
    private final UpdateUserUseCase updateUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final Map<Provider, OAuthClientPort> oAuthClientPortMap;

    public OAuthLoginResponse process(Provider provider, String accessToken) {
        OAuthDetailResponse response = oAuthClientPortMap.get(provider)
            .retrieveOAuthDetail(accessToken);
        JwtTokenResponse jwtTokenInfo = jwtTokenProvider.createTokenInfo(response.email());

        boolean isRegistered = isRegistered(response, jwtTokenInfo);
        String nickname = retrieveUserQuery.retrieveUserNicknameByEmail(response.email());

        log.info("OAuthLoginCommand process() end {}, {}, {}", isRegistered, response.email(), accessToken);

        return new OAuthLoginResponse(
            jwtTokenInfo.accessToken(),
            jwtTokenInfo.refreshToken(),
            provider,
            nickname,
            isRegistered);
    }

    private boolean isRegistered(OAuthDetailResponse response, JwtTokenResponse jwtTokenInfo) {
        boolean isRegistered = retrieveUserQuery.isUserRegisteredByEmail(response.email());
        updateRefreshTokenIfRegistered(isRegistered, response.email(), jwtTokenInfo.refreshToken());

        if (isRegistered) {
            applicationEventPublisher.publishEvent(new LoginUserEvent(this, response.email()));
        }

        return isRegistered;
    }

    private void updateRefreshTokenIfRegistered(boolean isRegistered, String email,
        String refreshToken) {
        if (isRegistered) {
            updateUserUseCase.updateRefreshToken(email, refreshToken);
        }
    }
}
