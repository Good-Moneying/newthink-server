package kusitms.duduk.application.security.config;

import java.util.Map;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.application.security.api.GoogleApiClient;
import kusitms.duduk.application.security.api.KakaoApiClient;
import kusitms.duduk.application.security.api.NaverApiClient;
import kusitms.duduk.core.security.port.output.OAuthClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class OAuthConfig {

    private final GoogleApiClient googleApiClient;
    private final KakaoApiClient kakaoApiClient;
    private final NaverApiClient naverApiClient;

    @Bean
    public Map<Provider, OAuthClientPort> oAuthClientPortMap() {
        return Map.of(
            Provider.GOOGLE, googleApiClient,
            Provider.KAKAO, kakaoApiClient,
            Provider.NAVER, naverApiClient
        );
    }
}
