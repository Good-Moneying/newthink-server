package kusitms.duduk.core.user.port.output;

import java.util.Optional;
import kusitms.duduk.domain.user.User;

public interface UpdateUserPort {

    Optional<User> update(User user);
}
