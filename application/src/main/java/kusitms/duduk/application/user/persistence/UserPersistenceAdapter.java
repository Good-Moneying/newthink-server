package kusitms.duduk.application.user.persistence;

import java.util.Optional;

import kusitms.duduk.application.user.persistence.entity.UserJpaEntity;
import kusitms.duduk.core.annotation.Adapter;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class UserPersistenceAdapter implements UpdateUserPort, LoadUserPort, SaveUserPort,
    DeleteUserPort {

    private final UserRepository userRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public User save(User user) {
        // UserJpaEntity로 변환하고
        UserJpaEntity userJpaEntity = userJpaMapper.toJpaEntity(user);
        // 변환한 UserJpaEntity를 저장 (Id 값이 자동으로 생성)
        UserJpaEntity userSaved = userRepository.save(userJpaEntity);
        // Id 값과 함께 반환
        return userJpaMapper.toDomain(userSaved);
    }

    @Override
    public void saveAndFlush(User user) {
        UserJpaEntity userJpaEntity = userJpaMapper.toJpaEntity(user);
        userRepository.saveAndFlush(userJpaEntity);
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


    @Override
    public Optional<User> update(User user) {
        Long userId = user.getId().getValue();

        return userRepository.findById(userId)
            .map(persistedUserData -> userJpaMapper.toJpaEntity(user, persistedUserData))
            .map(userRepository::save)
            .map(userJpaMapper::toDomain);
    }

    @Override
    public void deleteById(Id userId) {
        userRepository.deleteById(userId.getValue());
    }
}