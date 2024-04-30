package kusitms.duduk.domain.user.application.port.in;

import kusitms.duduk.domain.user.domain.User;
import kusitms.duduk.domain.user.dto.CreateUserRequest;

public interface RegisterUserUseCase {
    void register(CreateUserRequest createUserRequest);
}
