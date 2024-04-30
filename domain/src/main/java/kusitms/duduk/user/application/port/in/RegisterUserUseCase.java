package kusitms.duduk.user.application.port.in;

import kusitms.duduk.user.dto.CreateUserRequest;

public interface RegisterUserUseCase {
    void register(CreateUserRequest createUserRequest);
}
