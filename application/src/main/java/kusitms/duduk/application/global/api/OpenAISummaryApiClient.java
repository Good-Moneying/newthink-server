package kusitms.duduk.application.global.api;

import jakarta.annotation.PostConstruct;
import kusitms.duduk.core.comment.dto.request.OpenAISummaryRequest;
import kusitms.duduk.core.openai.dto.request.OpenAiSummaryRequest;
import kusitms.duduk.core.openai.dto.response.OpenAiSummaryResponse;
import kusitms.duduk.core.openai.port.output.OpenAISummaryClientPort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OpenAISummaryApiClient implements OpenAISummaryClientPort {

    private final String OPENAI_BASE_URL = "https://api.openai.com/v1/chat/completions";
    private final String OPENAI_API_KEY = "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";
    private WebClient webClient;

    @PostConstruct
    private void init() {
        this.webClient = WebClient.builder()
            .baseUrl(OPENAI_BASE_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Authorization", "Bearer " + OPENAI_API_KEY)
            .build();
    }

    @Override
    public String summarize(OpenAISummaryRequest request) {
        return webClient.post()
            .bodyValue(new OpenAiSummaryRequest(request.comment()))
            .retrieve()
            .bodyToMono(OpenAiSummaryResponse.class)
            .map(OpenAiSummaryResponse::extractContent)
            .block();
    }


}
