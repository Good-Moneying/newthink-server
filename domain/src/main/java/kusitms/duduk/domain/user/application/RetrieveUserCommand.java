package kusitms.duduk.domain.user.application;

import kusitms.duduk.domain.user.adapter.out.persistence.UserRepository;
import kusitms.duduk.domain.user.application.port.in.RetrieveUserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RetrieveUserCommand implements RetrieveUserQuery {
    private final UserRepository userRepository;

    @Override
    public boolean isUserRegisteredByEmail(String email) {
        return userRepository.isExistsByEmail(email);
    }
}
