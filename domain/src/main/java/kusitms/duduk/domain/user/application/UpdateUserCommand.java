package kusitms.duduk.domain.user.application;

import kusitms.duduk.domain.user.application.port.in.UpdateUserUseCase;
import kusitms.duduk.domain.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserCommand implements UpdateUserUseCase {

    private final UserRepository userRepository;

    public void updateRefreshToken(String email, String refreshToken) {
        userRepository.findByEmail(email)
            .ifPresent(user -> {
	user.updateRefreshToken(refreshToken);
	userRepository.save(user);
            });
    }
}
