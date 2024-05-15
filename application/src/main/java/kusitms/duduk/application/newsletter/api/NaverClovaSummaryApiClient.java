package kusitms.duduk.application.newsletter.api;

import io.jsonwebtoken.lang.Assert;
import jakarta.annotation.PostConstruct;
import kusitms.duduk.core.newsletter.dto.request.NaverClovaSummaryRequest;
import kusitms.duduk.core.newsletter.dto.response.NaverClovaSummaryResponse;
import kusitms.duduk.core.newsletter.port.output.NaverClovaSummaryClientPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class NaverClovaSummaryApiClient implements NaverClovaSummaryClientPort {

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    private static final String NAVER_CLOVA_BASE_URL = "https://naveropenapi.apigw.ntruss.com";

    private WebClient webClient;
    @PostConstruct
    private void init() {
        this.webClient = WebClient.builder()
            .baseUrl(NAVER_CLOVA_BASE_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-NCP-APIGW-API-KEY-ID", clientId)
            .defaultHeader("X-NCP-APIGW-API-KEY", clientSecret)
            .build();
    }

    public NaverClovaSummaryResponse summarize(NewsLetter newsLetter) {
        Assert.notNull(newsLetter.getTitle(), "제목은 null 일 수 없습니다.");
        Assert.notNull(newsLetter.getContent(), "내용은 null 일 수 없습니다.");

        NaverClovaSummaryRequest request = NaverClovaSummaryRequest.of(
            newsLetter.getTitle().getTitle(), newsLetter.getContent().getContent());

        return webClient.post()
            .uri("/text-summary/v1/summarize")
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(NaverClovaSummaryResponse.class)
            .block();
    }
}


