package kusitms.duduk.user.adapter.out.web.persistence;


import java.util.Optional;
import kusitms.duduk.user.adapter.out.persistence.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long>, UserRepositoryCustom {

    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByRefreshToken(String refreshToken);

    boolean existsByEmail(String email);
}
