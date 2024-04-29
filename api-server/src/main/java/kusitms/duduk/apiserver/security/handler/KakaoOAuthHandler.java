package kusitms.duduk.apiserver.security.handler;

import com.fasterxml.jackson.databind.JsonNode;
import kusitms.duduk.apiserver.security.presentation.dto.OAuthDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
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

        return webClient.post()
            .uri(uriBuilder -> uriBuilder.path("/v2/user/me")
                .queryParam("property_keys", "[\"kakao_account.email\"]")
                .build())
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .doOnNext(jsonNode -> log.info("Received JsonNode: " + jsonNode)) // JsonNode 확인
            .map(jsonNode -> new OAuthDetailResponse(jsonNode.get("kakao_account").get("email").asText()))
            .block();
    }
}
