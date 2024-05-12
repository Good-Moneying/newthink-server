package kusitms.duduk.application.user.service;

import static kusitms.duduk.domain.global.TestProperties.*;
import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("RegisterUserCommand 테스트")
class RegisterUserCommandTest {

    @Autowired
    private RegisterUserUseCase registerUserUseCase;

    @Autowired
    private LoadUserPort loadUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
    }

    @Test
    void 회원가입을_한다() {
        // when
        UserResponse response = registerUserUseCase.register(UserSteps.createUserRequest_생성());

        // then
        assertThat(response).isNotNull();
        assertThat(response.email()).isEqualTo(EMAIL);
        assertThat(response.nickname()).isEqualTo(NICKNAME);
        assertThat(response.archives().size()).isGreaterThan(0);

        assertThat(loadUserPort.existsUserByEmail(EMAIL)).isTrue();
    }
}