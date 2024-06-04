package kusitms.duduk.application.user.service;

import static kusitms.duduk.common.exception.ErrorMessage.USER_NOT_FOUND;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.user.port.input.WithdrawUserUseCase;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WithdrawUserCommand implements WithdrawUserUseCase {

    private final LoadUserPort loadUserPort;
    private final DeleteUserPort deleteUserPort;

    @Transactional
    @Override
    public void withdraw(String email) {
        User user = getUserByEmail(email);
        deleteUserPort.deleteById(user.getId().getValue());
    }

    private User getUserByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
    }
}
