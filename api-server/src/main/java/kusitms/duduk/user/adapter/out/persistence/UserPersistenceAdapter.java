package kusitms.duduk.user.adapter.out.persistence;

import java.util.Optional;
import kusitms.duduk.user.adapter.out.web.persistence.UserRepository;

import kusitms.duduk.user.port.out.LoadUserPort;
import kusitms.duduk.user.port.out.SaveUserPort;
import kusitms.duduk.user.port.out.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements UpdateUserPort, LoadUserPort, SaveUserPort {

    private final UserRepository userRepository;

    @Override
    public void save(UserJpaEntity user) {
        userRepository.save(user);
    }

    @Override
    public void saveAndFlush(UserJpaEntity user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<UserJpaEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
