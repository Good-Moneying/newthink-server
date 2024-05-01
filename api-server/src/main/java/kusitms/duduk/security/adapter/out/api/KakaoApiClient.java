package kusitms.duduk.security.adapter.out.api;

import com.fasterxml.jackson.databind.JsonNode;
import kusitms.duduk.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.security.port.out.OAuthClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class KakaoApiClient implements OAuthClientPort {
    private static final String KAKAO_BASE_URL = "https://kapi.kakao.com";

    @Qualifier("oAuthWebClient")
    private final WebClient webClient;

    public OAuthDetailResponse retrieveOAuthDetail(String accessToken) {
        return webClient.post()
            .uri(uriBuilder -> uriBuilder
	.path(KAKAO_BASE_URL)
	.path("/v2/user/me")
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
