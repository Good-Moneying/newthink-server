package kusitms.duduk.application.user.service;

import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterUserCommand implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;

    @Override
    public void register(CreateUserRequest request) {
        User user = User.create(request.email(), request.nickname(), request.birthDay());

        saveUserPort.save(user);
    }
}
