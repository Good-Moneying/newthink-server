package kusitms.duduk.application.security.api;

import com.fasterxml.jackson.databind.JsonNode;
import kusitms.duduk.core.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.core.security.port.output.OAuthClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class KakaoApiClient implements OAuthClientPort {
    private static final String KAKAO_BASE_URL = "https://kapi.kakao.com";

    private final WebClient webClient = WebClient.builder()
        .baseUrl(KAKAO_BASE_URL)
        .defaultHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .build();

    public OAuthDetailResponse retrieveOAuthDetail(String accessToken) {
        return webClient.post()
            .uri(uriBuilder -> uriBuilder
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
