package kusitms.duduk.application.user.service;

import static kusitms.duduk.domain.global.TestProperties.*;
import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegisterUserCommandTest {

    @Autowired
    private RegisterUserUseCase registerUserUseCase;

    @Autowired
    private LoadUserPort loadUserPort;

    @Test
    void 회원가입을_한다() {
        // given
        CreateUserRequest createUserRequest = new CreateUserRequest(
            EMAIL,
            NICKNAME,
            REFRESH_TOKEN,
            GENDER_TEXT,
            BIRTHDAY,
            PROVIDER.name(),
            CATEGORY_STOCK.name(),
            GOAL_EVERYDAY.name()
        );

        // when
        UserResponse response = registerUserUseCase.register(createUserRequest);

        // then
        assertThat(response).isNotNull();
        assertThat(response.email()).isEqualTo(EMAIL);
        assertThat(response.nickname()).isEqualTo(NICKNAME);

        assertThat(loadUserPort.existsUserByEmail(EMAIL)).isTrue();
    }
}