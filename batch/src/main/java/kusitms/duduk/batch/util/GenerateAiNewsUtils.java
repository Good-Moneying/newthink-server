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

        String query = "다음으로 주어지는 뉴스를 최소 300자에서 500자 이내로 요약해서 뉴스레터 내용을 작성해줘. 형식은 이렇게 작성해줘. "
                + "“본문: “ 이후로 나오는 내용이 주어지는 뉴스이고 "
                + "“제목: ” 이후로는 주어진 뉴스의 제목을 작성해줘. "
                + "“키워드를 1~3개 사이로 알려줘! "
                + "“카테고리를 알려줘! 카테고리 종류는 FINANCE, POLICY, REAL_ESTATE, SECURITIES"
                + crawlingNews.getContent()
                + "\n 제목:"
                + crawlingNews.getTitle()
                + "\n 형식은 다음과 같아"
            + "{"
            + "headline: 제목, "
            + "content: 본문, "
            + "keywords: 키워드, "
            + "category: 카테고리"
            + "}";

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
