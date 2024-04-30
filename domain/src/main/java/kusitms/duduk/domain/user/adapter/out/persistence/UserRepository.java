package kusitms.duduk.domain.user.adapter.out.persistence;

import java.util.Optional;
import kusitms.duduk.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByEmail(String email);

    Optional<User> findByRefreshToken(String refreshToken);

    boolean isExistsByEmail(String email);
}