package kusitms.duduk.domain.user.application;

import kusitms.duduk.domain.user.application.port.in.RegisterUserUseCase;
import kusitms.duduk.domain.user.domain.User;
import kusitms.duduk.domain.user.adapter.out.persistence.UserRepository;
import kusitms.duduk.domain.user.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterUserCommand implements RegisterUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void register(CreateUserRequest request) {
        User user = User.create(request.email(), request.nickname(), request.birthDay());

        userRepository.save(user);
    }
}
