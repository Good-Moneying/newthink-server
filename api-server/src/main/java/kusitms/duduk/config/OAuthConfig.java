package kusitms.duduk.config;

import java.util.Map;
import kusitms.duduk.security.handler.GoogleOAuthHandler;
import kusitms.duduk.security.handler.KakaoOAuthHandler;
import kusitms.duduk.security.handler.NaverOAuthHandler;
import kusitms.duduk.security.handler.OAuthHandler;
import kusitms.duduk.security.domain.Provider;
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
