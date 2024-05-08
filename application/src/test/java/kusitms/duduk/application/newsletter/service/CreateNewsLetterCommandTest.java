package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.CreateAiNewsLetterRequest;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.domain.newsletter.NewsLetter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("CreateNewsLetterCommandTest 테스트")
public class CreateNewsLetterCommandTest {

    @Autowired
    private CreateNewsLetterUseCase createNewsLetterCommand;

    @Test
    void AI_뉴스_레터를_생성한다() {
        // given
        CreateAiNewsLetterRequest request = 뉴스_레터_생성_요청();

        // when
        NewsLetter newsLetter = createNewsLetterCommand.create(request);

        // then
        assertThat(newsLetter).isNotNull();
        assertThat(newsLetter.getSummary().getSentences().size()).isEqualTo(3);
        assertThat(newsLetter.getSummary().getSentences().get(0)).isEqualTo("물가 상승률이 예상보다 높다.");
    }

    private static CreateAiNewsLetterRequest 뉴스_레터_생성_요청() {
        String thumbnail = "https://image.com/tesla.jpg";
        String title = "테슬라 주가 갑자기 오르는 이유는?";
        String content = "불과 몇 주 사이에 미국 토화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까진 다들 '오해 6월에 금리 인하가 시작될 것'이라고 했다";
        String keywords = "테슬라, 전기차, 미국 연준";
        String category = "Stock";
        String summary = "물가 상승률이 예상보다 높다.\n급격한 기준 금리 인상한다\n이민자 증가한다\n";
        final String aiType = "AI";

        CreateAiNewsLetterRequest request = new CreateAiNewsLetterRequest(
            thumbnail,
            title,
            content,
            keywords,
            category,
            summary,
            aiType
        );
        return request;
    }
}
