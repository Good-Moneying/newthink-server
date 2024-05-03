package kusitms.duduk.application.user.persistence;

import java.util.Optional;

import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements UpdateUserPort, LoadUserPort, SaveUserPort {

    private final UserRepository userRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public void save(User user) {
        userRepository.save(userJpaMapper.toJpaEntity(user));
    }

    @Override
    public void saveAndFlush(User user) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(userJpaMapper::toDomain);
    }

    @Override
    public Optional<User> findByRefreshToken(String refreshToken) {
        return userRepository.findByRefreshToken(refreshToken)
            .map(userJpaMapper::toDomain);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}

/**
 * @Adapter class ReadUserAdapter implements ReadUserPort {
 * <p>
 * private final UserRepository userRepository;
 * <p>
 * private final UserJpaMapper userJpaMapper;
 * <p>
 * public ReadUserAdapter(UserRepository userRepository, UserJpaMapper userJpaMapper) {
 * this.userRepository = userRepository; this.userJpaMapper = userJpaMapper; }
 * @Override public Boolean existsUserByName(User user) { String firstName =
 * userFirstName.apply(user); String lastName = userLastName.apply(user); return
 * !userRepository.findByFirstNameAndLastName(firstName, lastName) .isEmpty(); }
 * @Override public Boolean existsUserById(UserId userId) { Integer userIdAsInt = userId.intValue();
 * return userRepository.existsById(userIdAsInt); }
 * @Override public Optional<User> fetchById(UserId userId) { return
 * userRepository.findById(userId.intValue()) .map(userJpaMapper::toDomain); }
 * @Override public List<User> fetchAll() { return userRepository.findAll() .stream()
 * .map(userJpaMapper::toDomain) .collect(Collectors.toUnmodifiableList()); } }
 */