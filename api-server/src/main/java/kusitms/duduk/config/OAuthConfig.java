package kusitms.duduk.config;

import java.util.Map;
import kusitms.duduk.user.Provider;
import kusitms.duduk.security.adapter.out.api.GoogleApiClient;
import kusitms.duduk.security.adapter.out.api.KakaoApiClient;
import kusitms.duduk.security.adapter.out.api.NaverApiClient;
import kusitms.duduk.security.port.out.OAuthClientPort;
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
