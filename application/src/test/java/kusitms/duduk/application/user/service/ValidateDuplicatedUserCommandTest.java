package kusitms.duduk.application.user.service;

import kusitms.duduk.common.exception.custom.AlreadyExistsException;
import kusitms.duduk.core.user.dto.request.ValidateUserEmailRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserNicknameRequest;
import kusitms.duduk.core.user.port.input.ValidateDuplicatedUserQuery;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ValidateDuplicatedUserCommandTest {

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private ValidateDuplicatedUserQuery validateDuplicatedUserQuery;

    @BeforeEach
    void setup() {
        saveUserPort.create(UserSteps.ROLE_USER_생성_요청());
    }

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
    }

    @Test
    void 중복된_이메일로_가입할_수_없다() {
        // given
        String email = "test1@test.com";
        ValidateUserEmailRequest request = new ValidateUserEmailRequest(email);

        // when &then
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            validateDuplicatedUserQuery.validateDuplicatedEmail(request);
        });
    }

    @Test
    void 중복된_닉네임으로_가입할_수_없다() {
        // given
        String nickname = "tester1";
        ValidateUserNicknameRequest request = new ValidateUserNicknameRequest(nickname);

        // when & then
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            validateDuplicatedUserQuery.validateDuplicatedNickname(request);
        });
    }
}
