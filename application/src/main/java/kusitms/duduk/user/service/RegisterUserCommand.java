package kusitms.duduk.user.service;

import kusitms.duduk.user.adapter.out.persistence.UserJpaEntity;
import kusitms.duduk.user.dto.request.CreateUserRequest;
import kusitms.duduk.user.port.in.RegisterUserUseCase;
import kusitms.duduk.user.port.out.SaveUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterUserCommand implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;

    @Override
    public void register(CreateUserRequest request) {
        UserJpaEntity user = UserJpaEntity.create(request.email(), request.nickname(), request.birthDay());

        saveUserPort.save(user);
    }
}
