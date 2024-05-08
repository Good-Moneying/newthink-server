package kusitms.duduk.apiserver.security.infrastructure.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.application.security.service.JwtTokenProvider;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;

    public static final String BEARER_PREFIX = "Bearer ";
    @Value("${jwt.access.header}")
    private String accessTokenHeader;

    @Value("${jwt.refresh.header}")
    private String refreshTokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        /**
         * OAuth 토큰을 추출합니다. 구별을 위해 OAuth prefix를 사용합니다.
         */
        String oAuthToken = extractOAuthToken(request)
            .orElse(null);

        /**
         * OAuth Token이 null이 아니라면 로그인 요청이니 다음 필터로 바로 넘어갑니다.
         */
        if (oAuthToken != null) {
            filterChain.doFilter(request, response);
            return;
        }

        /**
         * 사용자 요청 헤더에서 RefreshToken을 추출합니다.
         * RefreshToken이 없거나 유효하지 않다면 null을 반환합니다.
         * 사용자 요청 헤더에 RefreshToken이 있는 경우는 AccessToken이 만료되었을 때입니다.
         * AccessToken이 만료되었다면 (403 ERROR) 후 클라이언트가 다시 요청합니다.
         */
        try {
            String refreshToken = extractRefreshToken(request)
	.filter(jwtTokenProvider::isTokenValid)
	.orElse(null);
            /**
             * RefreshToken이 헤더에 존재하고 유효하다면 403 에러 (AccessToken 만료) 가 발생한 것입니다.
             * User DB의 리프레시 토큰과 일치하는지 판단 후 일치 한다면 AccessToken을 재발급합니다.
             */
            if (refreshToken != null) {
	verifyRefreshTokenAndReIssueAccessToken(response, refreshToken);
	return;
            }
            /**
             * RefreshToken이 없거나 유효하지 않은 경우 AccessToken을 추출합니다.
             * AccessToken 마저 없다면 403 에러를 반환합니다.
             */
            if (refreshToken == null) {
	verifyAccessTokenAndSaveAuthentication(request, response, filterChain);
	return;
            }
        } catch (Exception e) {
            log.info("RefreshToken is not valid");
        }

        filterChain.doFilter(request, response);
    }

    private void verifyRefreshTokenAndReIssueAccessToken(HttpServletResponse response,
        String refreshToken) {
        loadUserPort.findByRefreshToken(refreshToken)
            .ifPresent(user -> {
	String reIssuedRefreshToken = reIssueRefreshToken(user);
	sendAccessAndRefreshToken(response,
	    jwtTokenProvider.createAccessToken(user.getEmail().getValue()),
	    reIssuedRefreshToken);
            });
    }

    private String reIssueRefreshToken(User user) {
        String reIssuedRefreshToken = jwtTokenProvider.createRefreshToken(
            user.getEmail().getValue());
        user.updateRefreshToken(reIssuedRefreshToken);
        saveUserPort.saveAndFlush(user);
        return reIssuedRefreshToken;
    }

    private void verifyAccessTokenAndSaveAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        Optional<String> accessToken = extractAccessToken(request);

        if(accessToken.isEmpty()){
            filterChain.doFilter(request, response);
            return;
        }
        /**
         * Authentication 객체는 해당 클래스에서 혹은 JwtTokenProvider에서 처리해도 무방합니다.
         * 다만, getSubject()를 통해 email을 추출하고 email을 통해 User 정보를 가져올 수 있으니
         * 해당 정보를 사용해서 UserDetails 혹은 OAuth2User를 만든다면 위 클래스에서 진행하는 것이 나을 수 있습니다.
         * 현재 경우에는 accessToken에 담겨 있는 email, Authorities 정보로 Authentication 객체를 생성합니다.
         */
        if(jwtTokenProvider.isTokenValid(accessToken.get())){
            jwtTokenProvider.getSubject(accessToken.get())
	.ifPresent(email -> loadUserPort.findByEmail(email)
	    .ifPresent(this::saveAuthentication));
        }

        filterChain.doFilter(request, response);
    }

    private void saveAuthentication(User user) {
        CustomUserDetails userDetails = new CustomUserDetails(user);
        Authentication authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null,
	userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessTokenHeader))
            .filter(refreshToken -> StringUtils.hasText(refreshToken))
            .filter(
	refreshToken -> refreshToken.startsWith(BEARER_PREFIX))  // 토큰이 'Bearer '로 시작하는지 확인
            .map(refreshToken -> refreshToken.substring(
	BEARER_PREFIX.length()));  // 'Bearer ' 접두어 제거
    }

    private Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshTokenHeader))
            .filter(StringUtils::hasText)  // StringUtils.hasText()를 이용하여 텍스트가 실제로 존재하는지 확인
            .filter(
	refreshToken -> refreshToken.startsWith(BEARER_PREFIX))  // 토큰이 'Bearer '로 시작하는지 확인
            .map(refreshToken -> refreshToken.substring(
	BEARER_PREFIX.length()));  // 'Bearer ' 접두어 제거
    }

    private Optional<String> extractOAuthToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("OAuth"))
            .filter(oAuthToken -> StringUtils.hasText(oAuthToken))
            .map(oAuthToken -> oAuthToken.toLowerCase(Locale.ROOT));
    }

    private void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken,
        String reIssuedRefreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(accessTokenHeader, accessToken);
        response.setHeader(refreshTokenHeader, reIssuedRefreshToken);
    }
}