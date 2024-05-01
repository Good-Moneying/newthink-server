package kusitms.duduk.user.port.out;

import java.util.Optional;
import kusitms.duduk.user.adapter.out.persistence.UserJpaEntity;

public interface LoadUserPort {

    Optional<UserJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
