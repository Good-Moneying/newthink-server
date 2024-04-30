package kusitms.duduk.user.adapter.out.persistence;

import kusitms.duduk.user.domain.User;

public class UserMapper {
    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        return new User(userJpaEntity.getId(), userJpaEntity.getName(), userJpaEntity.getEmail());
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        return new UserJpaEntity(user.getId(), user.getName(), user.getEmail());
    }
}
