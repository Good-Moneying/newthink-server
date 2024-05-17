package kusitms.duduk.core.user.port.output;


import java.util.Optional;
import kusitms.duduk.domain.user.User;

public interface LoadUserPort {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsUserByEmail(String email);
    boolean existsUserByNickname(String nickname);

    Optional<User> findByRefreshToken(String refreshToken);
}
