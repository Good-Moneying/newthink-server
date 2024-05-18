package kusitms.duduk.application.ai.api;

import kusitms.duduk.core.ai.dto.request.OpenAIMessages;
import kusitms.duduk.core.ai.dto.request.OpenAIRequest;
import kusitms.duduk.core.ai.dto.response.OpenAIResponse;
import kusitms.duduk.core.ai.port.output.AiClientPort;
import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class OpenAiApiClient implements AiClientPort {

    private final String OPENAI_BASE_URL = "https://api.openai.com/v1/chat/completions";
    private final String OPENAI_API_KEY = "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";


    private final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public OpenAIResponse retrieveAiResponse(CrawlingNewsResponse crawlingNews) {
        String query = "다음으로 주어지는 뉴스를 최소 300자에서 500자 이내로 요약해서 뉴스레터 내용을 작성해줘. "
            + "형식은 이렇게 작성해줘. “본문: “ 이후에 요약한 내용을 써주면 돼. "
            + "“제목: ” 이후로는 주어진 뉴스의 제목을 작성해줘. "
            + "”키워드: ” 이후로는 뉴스 내용에서 키워드를 3가지 추출해줘. "
            + "”카테고리: ” 이후로는 FINANCE, POLICY, REAL_ESTATE, SECURITIES 중에 뉴스 내용과 가장 연관성이 있다고 생각하는 것을 하나 골라줘. "
            + "FINANCE 라고만 대답하지는 마. \n"
            + "뉴스: "
            + crawlingNews.getContent()
            + " \n본문: \n제목: "
            + crawlingNews.getTitle()
            + "\n키워드: \n카테고리: ";

        WebClient webClient = WebClient.builder()
            .baseUrl(OPENAI_BASE_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .build();

        OpenAIRequest openAIRequest = OpenAIRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(new ArrayList<>(Arrays.asList(
	OpenAIMessages.builder()
	    .role("user")
	    .content(query)
	    .build()
            )))
            .build();

        return webClient.post()
            .header(AUTHORIZATION_HEADER, "Bearer " + OPENAI_API_KEY)
            .body(BodyInserters.fromValue(openAIRequest))
            .retrieve()
            .bodyToMono(OpenAIResponse.class).block();
    }
}
