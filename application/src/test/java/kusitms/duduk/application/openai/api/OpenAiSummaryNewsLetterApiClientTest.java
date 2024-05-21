package kusitms.duduk.application.openai.api;

import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.core.openai.dto.request.OpenAiSummaryNewsLetterRequest;
import kusitms.duduk.core.openai.dto.response.OpenAiSummaryNewsLetterResponse;
import kusitms.duduk.core.openai.port.output.OpenAiClientPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAiSummaryNewsLetterApiClientTest {

    @Autowired
    private OpenAiClientPort<OpenAiSummaryNewsLetterRequest, OpenAiSummaryNewsLetterResponse> openAiClientPort;

    @Test
    void 뉴스레터_요약과_카테고리_키워드를_반환한다() {
        // given
        final String sampleContent = "미국의 금리가 다시 인상될 가능성이 제기되고 있습니다.";
        final OpenAiSummaryNewsLetterRequest sampleRequest = new OpenAiSummaryNewsLetterRequest(
            sampleContent);

        // when
        OpenAiSummaryNewsLetterResponse response = openAiClientPort.chat(sampleRequest);

        System.out.println(response);
        // then
        assertThat(response).isNotNull();
        assertThat(response.keywords()).isNotEmpty();
        assertThat(response.category()).isNotEmpty();
        assertThat(response.content()).isNotEmpty();

    }

}