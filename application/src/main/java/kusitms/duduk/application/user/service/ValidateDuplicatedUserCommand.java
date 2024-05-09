package kusitms.duduk.application.user.service;

import kusitms.duduk.core.exception.custom.AlreadyExistsException;
import kusitms.duduk.core.user.port.input.ValidateDuplicatedUserQuery;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ValidateDuplicatedUserCommand implements ValidateDuplicatedUserQuery {

    private final LoadUserPort loadUserPort;

    @Override
    public void validateDuplicatedEmail(String email) {
        log.info("Attempt to signup with duplicated email: {}\n", email);

        if (loadUserPort.existsUserByEmail(email)) {
            throw new AlreadyExistsException("이미 존재하는 이메일입니다.");
        }
    }

    @Override
    public void validateDuplicatedNickname(String nickname) {
        log.info("Attempt to signup with duplicated nickname: {}", nickname);
        if (loadUserPort.existsUserByNickname(nickname)) {
            throw new AlreadyExistsException("이미 존재하는 닉네임입니다.");
        }
    }
}
