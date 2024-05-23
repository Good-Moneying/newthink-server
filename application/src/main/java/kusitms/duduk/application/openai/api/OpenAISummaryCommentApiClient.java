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
    private static final String PROMPT = "다음으로 주어지는 댓글을 10자 이내의 짧은 글로 요악하는걸 해볼꺼야. 샘플 데이터는 다음과 같아"
        + "1. 미국이 금리를 인하하지 않으면 한국의 금리도 쉽게 인하되지 않을 것 같네…\n"
        + "> 미국과 한국의 금리\n"
        + "2. 미국은 경제 호황이라 괜찮지만, 지속적인 금리 인상은 한국 경제에 큰 타격이 있을 것 같아\n"
        + "> 미 금리 인상과 한국 경제 타격\n"
        + "3. 앞으로도 계속 금리가 인상된다면 소비가 심하게 위축되어서 경기 침체가 될거야\n"
        + "> 소비 위축과 경기 침체\n"
        + "4. 경기가 침체되면 실업율도 증가할텐데, 앞으로가 걱정되네\n"
        + "> 경기침체로 인한 실업율 증가\n"
        + "5. 실업을 막기 위해선 기업 활동을 저해하는 불필요한 규제를 완화해야해\n"
        + "> 기업 규제 완화 필요"
        + "String 값으로 반환해주면 좋겠어";

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
