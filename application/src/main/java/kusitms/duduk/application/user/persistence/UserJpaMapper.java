package kusitms.duduk.application.user.persistence;

import kusitms.duduk.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserJpaMapper {

    public UserJpaEntity toJpaEntity(User user) {
        return UserJpaEntity.create(user.getEmail(), user.getNickname(),
            user.getBirthday());
    }

    public User toDomain(UserJpaEntity userJpaEntity) {
        return User.create(userJpaEntity.getEmail(), userJpaEntity.getNickname(),
            userJpaEntity.getBirthday());
    }
}