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
                + "“요약 내용: “ 이후에 요약한 내용을 써주면 돼. "
                + "“제목: ” 이후로는 주어진 뉴스의 제목을 작성해줘. "
                + "“관련주: ” 에는 뉴스를 통해 가장 관련이 높다고 판단되는 주식 종목을 하나만 알려줘. "
                + "마지막으로 “퀴즈: ” 부분에는 뉴스 내용에 관한 퀴즈를 객관식으로 만들어줘. 객관식 선택지는 5개로 해줘. 정답도 번호로 알려줘. 아래는 예시야.\n 본문:"
                + crawlingNews.getContent()
                + "\n 제목:"
                + crawlingNews.getTitle()
                + "\n요약 내용: \n관련주: \n퀴즈: \n 1: \n 2: \n 3: \n 4: \n 5: "
                + "\n 정답: ";

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
