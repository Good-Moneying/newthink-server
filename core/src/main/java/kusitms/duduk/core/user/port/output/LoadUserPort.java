package kusitms.duduk.core.user.port.output;


import java.util.Optional;
import kusitms.duduk.domain.user.User;

public interface LoadUserPort {

    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);

    Optional<User> findByRefreshToken(String refreshToken);
}
