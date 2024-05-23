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
    private static final String PROMPT = "다음으로 주어지는 댓글을 12자 이내의 짧은 단어로 요약 해줘. "
        + "미국이 금리를 인하하지 않으면 한국의 금리도 쉽게 인하되지 않을 것 같네... -> 미국과 한국의 금리 등. "
        + "미국은 경제 호황이라 괜찮지만, 지속적인 금리 인상은 한국 경제에 큰 타격이 있을 것 같아 -> 미 금리 인상과 한국 경제 타격"
        + "앞으로도 계속 금리가 인상된다면 소비가 심하게 위축되어서 경기 침체가 될거야 -> 소비 위축과 경기 침체"
        + "String 값을 반환 하면 돼. 쉼표는 쓰지 말고 와나 과로 연결 해줘";

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
        return webClient.post()
            .bodyValue(new OpenAiRequest(PROMPT + request.comment()))
            .retrieve()
            .bodyToMono(OpenAiResponse.class)
            .map(OpenAiResponse::extractContent)
            .block();
    }
}
