package kusitms.duduk.user.application;

import kusitms.duduk.user.application.port.in.RegisterUserUseCase;
import kusitms.duduk.user.domain.User;
import kusitms.duduk.user.adapter.out.persistence.UserRepository;
import kusitms.duduk.user.dto.request.CreateUserRequest;
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
