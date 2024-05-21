package kusitms.duduk.application.user.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import kusitms.duduk.common.exception.custom.AlreadyExistsException;
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
        if (loadUserPort.existsUserByEmail(request.email())) {
            throw new AlreadyExistsException(EXIST_EMAIL.getMessage());
        }
    }

    @Override
    public void validateDuplicatedNickname(ValidateUserNicknameRequest request) {
        if (loadUserPort.existsUserByNickname(request.nickname())) {
            throw new AlreadyExistsException(EXIST_NICKNAME.getMessage());
        }
    }
}
