package kusitms.duduk.user.adapter.out.persistence;

import kusitms.duduk.user.User;

public class UserMapper {
    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        return new User(userJpaEntity.getEmail(), userJpaEntity.getNickname(), userJpaEntity.getBirthday());
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        return UserJpaEntity.create(user.getEmail(), user.getNickname(), user.getBirthday());
    }
}