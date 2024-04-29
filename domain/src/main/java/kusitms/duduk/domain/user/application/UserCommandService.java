package kusitms.duduk.domain.user.application;

import kusitms.duduk.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCommandService {

    private final UserRepository userRepository;

    public void updateRefreshToken(String email, String refreshToken) {
        userRepository.findByEmail(email)
            .ifPresent(user -> {
	user.updateRefreshToken(refreshToken);
	userRepository.save(user);
            });
    }
}
