package kusitms.duduk.application.thinking.service;

import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.application.newsletter.service.NewsLetterSteps;
import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.core.thinking.port.input.CreateThinkingUseCase;
import kusitms.duduk.core.thinking.port.output.SaveThinkingPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.thinking.Thinking;
import kusitms.duduk.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CreateThinkingCommandTest {

    @Autowired
    private CreateThinkingUseCase createThinkingUseCase;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private SaveNewsLetterPort saveNewsLetterPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private DeleteNewsLetterPort deleteNewsLetterPort;

    private User savedUser;
    private NewsLetter savedNewsLetter;

    @BeforeEach
    void setup() {
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);

        NewsLetter newsLetter = NewsLetterSteps.AI_FINANCE_뉴스_레터_생성();
        savedNewsLetter = saveNewsLetterPort.create(newsLetter);
    }

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
        deleteNewsLetterPort.deleteAll();
    }

    @Test
    void 생각을_생성한다() {
        // given
        CreateThinkingRequest request = CreateThinkingRequest.builder()
            .userId(savedUser.getId().getValue())
            .newsLetterId(savedNewsLetter.getId().getValue())
            .comment("요약 전 생각")
            .keywords("금융,경제,글로벌")
            .summarizedComment("요약 후 생각")
            .thumbnail("썸네일 주소")
            .build();

        // when
        Thinking thinking = createThinkingUseCase.create(request);

        // then
        assertThat(thinking).isNotNull();
        assertThat(thinking.getUserId().getValue()).isEqualTo(request.userId());
        assertThat(thinking.getNewsLetterId().getValue()).isEqualTo(request.newsLetterId());
        assertThat(thinking.getComment().getValue()).isEqualTo(request.comment());
        assertThat(thinking.getKeywords().getWords().size()).isEqualTo(3);
        assertThat(thinking.getSummarizedComment().getValue()).isEqualTo(
            request.summarizedComment());
    }
}
