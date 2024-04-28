package kusitms.duduk.apiserver.config;

import java.util.Map;
import kusitms.duduk.apiserver.security.handler.GoogleOAuthHandler;
import kusitms.duduk.apiserver.security.handler.KakaoOAuthHandler;
import kusitms.duduk.apiserver.security.handler.NaverOAuthHandler;
import kusitms.duduk.apiserver.security.handler.OAuthHandler;
import kusitms.duduk.domain.security.domain.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class OAuthConfig {

    private final GoogleOAuthHandler googleOAuthHandler;
    private final KakaoOAuthHandler kakaoOAuthHandler;
    private final NaverOAuthHandler naverOAuthHandler;

    @Bean
    public Map<Provider, OAuthHandler> oAuthHandlers() {
        return Map.of(
            Provider.GOOGLE, googleOAuthHandler,
            Provider.KAKAO, kakaoOAuthHandler,
            Provider.NAVER, naverOAuthHandler
        );
    }
}
