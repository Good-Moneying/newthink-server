package kusitms.duduk.application.thinking.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingHomeResponse;
import kusitms.duduk.core.thinking.port.output.DeleteThinkingPort;
import kusitms.duduk.core.thinking.port.output.LoadThinkingPort;
import kusitms.duduk.core.thinking.port.output.SaveThinkingPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.thinking.Thinking;
import kusitms.duduk.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetrieveThinkingCommandTest {

    @Autowired
    private RetrieveThinkingCommand retrieveThinkingCommand;

    @Autowired
    private SaveThinkingPort saveThinkingPort;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private DeleteThinkingPort deleteThinkingPort;

    private User savedUser;

    @BeforeEach
    void setup() {
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);
    }

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
        deleteThinkingPort.deleteAll();
    }

    @Test
    void 생각_더하기_홈을_조회한다() {
        // given
        ThinkingSteps.홈_생각_목록_조회_객체_생성(
	savedUser.getId().getValue())
            .forEach(
	thinking -> saveThinkingPort.save(thinking)
            );

        // when
        RetrieveThinkingHomeResponse response = retrieveThinkingCommand.retrieveThinkingHome(
            savedUser.getEmail().getValue());

        // then
        assertThat(response).isNotNull();
        assertThat(response.thinkingDetails().size()).isEqualTo(2);

        // 생각 구름이 작성 되지 않은 순으로 정렬 되는 것을 테스트 한다
        assertThat(response.thinkingDetails().get(0).isCloudExist()).isFalse();
        assertThat(response.thinkingDetails().get(1).isCloudExist()).isTrue();
    }

}