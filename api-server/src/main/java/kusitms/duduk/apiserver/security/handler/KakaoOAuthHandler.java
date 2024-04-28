package kusitms.duduk.apiserver.security.handler;

import kusitms.duduk.apiserver.security.presentation.dto.OAuthDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoOAuthHandler implements OAuthHandler {
    private static final String KAKAO_BASE_URL = "https://kapi.kakao.com";
    private WebClient webClient;

    public KakaoOAuthHandler() {
        this.webClient = WebClient.builder()
            .baseUrl(KAKAO_BASE_URL)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();
    }

    @Override
    public OAuthDetailResponse retrieveOAuthDetail(String accessToken) {


        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/v2/user/me")
                .queryParam("property_keys", "[\"kakao_account.email\"]")
                .build())
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .bodyToMono(OAuthDetailResponse.class)
            .block();
    }
}
