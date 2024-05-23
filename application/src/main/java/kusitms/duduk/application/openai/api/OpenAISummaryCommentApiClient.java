package kusitms.duduk.application.openai.api;

import jakarta.annotation.PostConstruct;
import kusitms.duduk.core.openai.dto.request.OpenAiSummaryCommentRequest;
import kusitms.duduk.core.openai.dto.request.OpenAiRequest;
import kusitms.duduk.core.openai.dto.response.OpenAiResponse;
import kusitms.duduk.core.openai.port.output.OpenAiClientPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class OpenAISummaryCommentApiClient implements
    OpenAiClientPort<OpenAiSummaryCommentRequest, String> {

    @Value("${openai.base-url}")
    private String OPENAI_BASE_URL;

    @Value("${openai.api-key}")
    private String OPENAI_API_KEY;

    private WebClient webClient;
    private static final String PROMPT = "다음으로 주어지는 댓글을 10자 이내의 짧은 단어로 요약 해줘."
        + "샘플은 다음과 같아. "
        + "1. 미국과 한국의 금리 등."
        + "2. 미 금리 인상과 한국 경제 타격,"
        + "3. 소비 위축과 경기 침체"
        + "쉼표 (comma) 쓰지마"
        + "String 값으로 반환해";

    @PostConstruct
    private void init() {
        this.webClient = WebClient.builder()
            .baseUrl(OPENAI_BASE_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Authorization", "Bearer " + OPENAI_API_KEY)
            .build();
    }

    @Override
    public String chat(OpenAiSummaryCommentRequest request) {
        log.info("Requesting OpenAI to summarize comment: {}", request.comment());

        return webClient.post()
            .bodyValue(new OpenAiRequest(PROMPT + request.comment()))
            .retrieve()
            .bodyToMono(OpenAiResponse.class)
            .map(OpenAiResponse::extractContent)
            .block();
    }
}
