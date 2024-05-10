package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.Assertions.*;

import io.jsonwebtoken.lang.Assert;
import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("RetrieveNewsLetterCommand 테스트")
@SpringBootTest
public class RetrieveNewsLetterCommandTest {

    @Autowired
    private SaveNewsLetterPort saveNewsLetterPort;

    @Autowired
    private LoadNewsLetterPort loadNewsLetterPort;

    @Autowired
    private NewsLetterDtoMapper newsLetterDtoMapper;

    private NewsLetter savedNewsLetter;

    @BeforeEach
    void setup() {
        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(NewsLetterSteps.뉴스_레터_생성_요청());
        savedNewsLetter = saveNewsLetterPort.save(newsLetter);
    }

    @Test
    void
    뉴스레터_아이디로_뉴스레터를_조회한다() {
        // given
        RetrieveNewsLetterRequest request = new RetrieveNewsLetterRequest(
            savedNewsLetter.getNewsLetterId().getValue());

        RetrieveNewsLetterCommand command = new RetrieveNewsLetterCommand(loadNewsLetterPort,
            newsLetterDtoMapper);

        // when
        NewsLetterResponse response = command.retrieve(request);

        // then
        assertThat(response.title()).isEqualTo(savedNewsLetter.getTitle().getTitle());
        assertThat(response.content()).isEqualTo(savedNewsLetter.getContent().getContent());
    }

    private record RetrieveNewsLetterRequest(Long id) {

        private RetrieveNewsLetterRequest {
            Assert.notNull(id, "뉴스레터 아이디는 필수입니다.");
        }
    }

    private record RetrieveNewsLetterCommand(LoadNewsLetterPort loadNewsLetterPort,
		             NewsLetterDtoMapper newsLetterDtoMapper) {

        public NewsLetterResponse retrieve(RetrieveNewsLetterRequest request) {
            NewsLetter newsLetter = loadNewsLetterPort.findById(request.id())
	.orElseThrow(() -> new IllegalArgumentException("해당 뉴스레터를 찾을 수 없습니다."));
            return newsLetterDtoMapper.toDto(newsLetter);
        }
    }
}
