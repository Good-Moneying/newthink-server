package kusitms.duduk.apiserver.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kusitms.duduk.domain.security.application.JwtTokenProvider;
import kusitms.duduk.domain.security.domain.CustomUserDetails;
import kusitms.duduk.domain.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) {
        // Authentication 객체에서 subject(email)를 추출하여 accessToken과 refreshToken을 생성합니다.
        String email = extractEmail(authentication);

        // 해당 email로 accessToken과 refreshToken을 생성합니다.
        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        // 헤더에서 accessToken과 refreshToken을 전송합니다.
        jwtTokenProvider.sendAccessAndRefreshToken(response, accessToken, refreshToken);

        userRepository.findByEmail(email)
            .ifPresent(user -> {
	user.updateRefreshToken(refreshToken);
	userRepository.saveAndFlush(user);
            });
    }

    private String extractEmail(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getEmail();
    }
}
