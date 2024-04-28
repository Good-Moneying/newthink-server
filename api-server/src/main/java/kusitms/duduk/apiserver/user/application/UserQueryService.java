package kusitms.duduk.apiserver.user.application;

import kusitms.duduk.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserQueryService {

    private final UserRepository userRepository;

    public boolean isUserRegisteredByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
