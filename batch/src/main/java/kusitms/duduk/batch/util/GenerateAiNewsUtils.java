package kusitms.duduk.batch.util;

import kusitms.duduk.batch.dto.crawling.CrawlingNews;
import kusitms.duduk.batch.dto.openai.OpenAIMessages;
import kusitms.duduk.batch.dto.openai.OpenAIRequest;
import kusitms.duduk.batch.dto.openai.OpenAIResponse;
import kusitms.duduk.batch.dto.openai.parsing.ParsedAiContent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class GenerateAiNewsUtils {

    @Value("open-ai.endpoint")
    private String END_POINT;

    @Value("open-ai.secret-key")
    private String OPEN_AI_SECRET_KEY;

    private final ParsingAiContentUtils parsingAiContent;

    public ParsedAiContent getAIResponse(CrawlingNews crawlingNews) {

        String query = "다음으로 주어지는 뉴스를 최소 300자에서 500자 이내로 요약해서 뉴스레터 내용을 작성해줘. "
                        + "형식은 이렇게 작성해줘. “본문: “ 이후에 요약한 내용을 써주면 돼. "
                        + "“제목: ” 이후로는 주어진 뉴스의 제목을 작성해줘. "
                        + "”키워드: ” 이후로는 뉴스 내용에서 키워드를 3가지 추출해줘. "
                        + "”카테고리: ” 이후로는 FINANCE, POLICY, REAL_ESTATE, SECURITIES 중에 뉴스 내용과 가장 연관성이 있다고 생각하는 것을 하나 골라줘. \n "
                        + "뉴스: "
                        + crawlingNews.getContent()
                        + " \n본문: \n제목: "
                        + crawlingNews.getTitle()
                        + "\n키워드: \n카테고리: ";

        WebClient webClient = WebClient.builder()
                .baseUrl(END_POINT)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();


        OpenAIRequest openAIRequest = OpenAIRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(new ArrayList<>())
                //.temperature(0.7)
                .build();

        openAIRequest.addMessage(
                OpenAIMessages.builder()
                        .role("user")
                        .content(query)
                        .build()
        );

        OpenAIResponse openAIResponse = webClient.post()
                .header("Authorization", "Bearer " + OPEN_AI_SECRET_KEY)
                .body(BodyInserters.fromValue(openAIRequest))
                .retrieve()
                .bodyToMono(OpenAIResponse.class).block();

        assert openAIResponse != null;
        return parsingAiContent.getParingResult(openAIResponse.getContent());
    }

}
