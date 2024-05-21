package kusitms.duduk.application.openai.api;

import jakarta.annotation.PostConstruct;
import kusitms.duduk.core.openai.dto.request.OpenAiRequest;
import kusitms.duduk.core.openai.dto.request.OpenAiSummaryNewsLetterRequest;
import kusitms.duduk.core.openai.dto.response.OpenAiResponse;
import kusitms.duduk.core.openai.dto.response.OpenAiSummaryNewsLetterResponse;
import kusitms.duduk.core.openai.port.output.OpenAiClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class OpenAiSummaryNewsLetterApiClient implements OpenAiClientPort<OpenAiSummaryNewsLetterRequest, OpenAiSummaryNewsLetterResponse> {

    //@Value("${openai.base-url}")
    private String OPENAI_BASE_URL = "https://api.openai.com/v1/chat/completions";

    //@Value("${openai.api-key}")
    private String OPENAI_API_KEY = "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";
    private WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String PROMPT = "다음으로 주어지는 뉴스를 300자에서 500자 이내로 요약한 뉴스 레터를 작성 해줘. "
        + "뉴스레터 샘플은 다음과 같아."
        + "뉴스레터 샘플 : \\n\\n\n"
        + "\n"
        + "불과 몇 주 사이에 미국 통화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까진 다들 ‘올해 6월에 금리 인하가 시작될 것’이라고 했는데, 이젠 ‘아직 멀었다’는 사람이 많아졌죠.\n"
        + "\n"
        + "\\n\\n\n"
        + "\n"
        + "지난 16일(현지시간) 제롬 파월 미국 연방준비제도(Fed·연준) 의장은 사실상 6월 금리 인하가 무산됐음을 인정했어요. 파월 의장은 “최근 데이터는 (금리 인하에 대한) 확신을 주지 못했고, 그런 확신을 얻는 데에는 예상보다 더 오랜 시간이 걸릴 것”이라고 말했어요.\n"
        + "\n"
        + "\\n\\n\n"
        + "\n"
        + "이제 미국의 기준금리 인하는 9월 이후에 가능하다는 전망이 우세하고, 인하 시점을 내년으로 보는 사람도 꽤 많아졌어요. 오히려 기준금리를 지금보다 조금 더 올릴 수 있다는 전망까지 나오고 있어요.\n"
        + "\n"
        + "\\n\\n\n"
        + "\n"
        + "왜 갑자기 분위기가 바뀐 걸까요? 경제 전문가들이 대부분 이런 변화를 예상하지 못했던 이유는 뭘까요?"
        + "추가적으로 해당 내용에 대한 키워드와 카테고리를 추출해줘."
        + "키워드는 3가지로 제한하고 , 로 구분하면 돼"
        + "카테고리는 FINANCE, POLICY, REAL_ESTATE, SECURITIES, COMPANY, TECH, LIFE, POLICY 중에 뉴스 내용과 가장 연관성이 있다고 생각하는 것을 하나 골라줘."
        + "응답 JSON 형식은 다음과 같아."
        + "{ keywords = \"keyword1, keyword2, keyword3\", category = \"category\", content = \"content\" }"
        + "요약할 뉴스 본문은 다음과 같아.";

    @PostConstruct
    private void init() {
        this.webClient = WebClient.builder()
            .baseUrl(OPENAI_BASE_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Authorization", "Bearer " + OPENAI_API_KEY)
            .build();
    }

    @Override
    public OpenAiSummaryNewsLetterResponse chat(OpenAiSummaryNewsLetterRequest request) {
        OpenAiResponse response = webClient.post()
            .bodyValue(new OpenAiRequest(PROMPT + request.content()))
            .retrieve()
            .bodyToMono(OpenAiResponse.class)
            .block();

        if (response == null) {
            throw new RuntimeException("Failed to get response from OpenAI");
        }

        String jsonContent = response.extractContent();
        try {
            return objectMapper.readValue(jsonContent, OpenAiSummaryNewsLetterResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse OpenAI response content", e);
        }
    }
}


