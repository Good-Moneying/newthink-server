package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("CreateNewsLetterCommandTest 테스트")
public class CreateNewsLetterCommandTest {

    @Autowired
    private CreateNewsLetterUseCase createNewsLetterCommand;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
    }

    @Test
    void 뉴스_레터를_생성한다() {
        // given
        CreateNewsLetterRequest request = NewsLetterSteps.AI_뉴스_레터_생성_요청();
        User user = UserSteps.ROLE_EDITOR_생성_요청();
        saveUserPort.create(user);

        // when
        NewsLetterResponse response = createNewsLetterCommand.create(request, user.getEmail().getValue());

        // then
        assertThat(response).isNotNull();
        assertThat(response.title()).isEqualTo(request.title());
        assertThat(response.content()).isEqualTo(request.content());
    }

    @Test
    void 편집_권한이_없으면_뉴스_레터를_생성할_수_없다() {
        // given
        CreateNewsLetterRequest request = NewsLetterSteps.AI_뉴스_레터_생성_요청();
        User user = UserSteps.ROLE_USER_생성_요청();
        saveUserPort.create(user);

        // when & then
        assertThatThrownBy(() -> createNewsLetterCommand.create(request, user.getEmail().getValue()))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
