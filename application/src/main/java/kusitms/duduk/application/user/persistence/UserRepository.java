package kusitms.duduk.application.user.persistence;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long>, UserRepositoryCustom {

    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByRefreshToken(String refreshToken);

    boolean existsByEmail(String email);
}
