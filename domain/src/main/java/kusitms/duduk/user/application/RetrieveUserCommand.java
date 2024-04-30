package kusitms.duduk.user.application;

import kusitms.duduk.user.adapter.out.persistence.UserRepository;
import kusitms.duduk.user.application.port.in.RetrieveUserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RetrieveUserCommand implements RetrieveUserQuery {
    private final UserRepository userRepository;

    @Override
    public boolean isUserRegisteredByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
