package kusitms.duduk.application.user.service;

import io.restassured.internal.common.assertion.Assertion;
import kusitms.duduk.core.exception.custom.AlreadyExistsException;
import kusitms.duduk.core.user.port.input.ValidateDuplicatedUserQuery;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidateDuplicatedUserCommandTest {

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private ValidateDuplicatedUserQuery validateDuplicatedUserQuery;

    @BeforeEach
    void setup() {
        saveUserPort.save(UserSteps.ROLE_USER_생성_요청());
    }

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
    }

    @Test
    void 중복된_이메일로_가입할_수_없다() {
        // given
        String email = "test1@test.com";

        // when &then
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            validateDuplicatedUserQuery.validateDuplicatedEmail(email);
        });
    }

    @Test
    void 중복된_닉네임으로_가입할_수_없다() {
        // given
        String nickname = "tester1";

        // when & then
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            validateDuplicatedUserQuery.validateDuplicatedNickname(nickname);
        });
    }
}
