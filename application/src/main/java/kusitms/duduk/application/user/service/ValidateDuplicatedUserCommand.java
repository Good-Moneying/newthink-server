package kusitms.duduk.application.user.service;

import kusitms.duduk.core.exception.custom.AlreadyExistsException;
import kusitms.duduk.core.user.dto.request.ValidateUserEmailRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserNicknameRequest;
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
    public void validateDuplicatedEmail(ValidateUserEmailRequest request) {
        log.info("Attempt to signup with duplicated email: {}\n", request.email());

        if (loadUserPort.existsUserByEmail(request.email())) {
            throw new AlreadyExistsException("이미 존재하는 이메일입니다.");
        }
    }

    @Override
    public void validateDuplicatedNickname(ValidateUserNicknameRequest request) {
        log.info("Attempt to signup with duplicated nickname: {}", request.nickname());
        if (loadUserPort.existsUserByNickname(request.nickname())) {
            throw new AlreadyExistsException("이미 존재하는 닉네임입니다.");
        }
    }
}
