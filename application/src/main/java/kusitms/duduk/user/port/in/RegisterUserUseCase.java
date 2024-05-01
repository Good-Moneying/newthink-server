package kusitms.duduk.user.port.in;

import kusitms.duduk.user.dto.request.CreateUserRequest;

public interface RegisterUserUseCase {
    void register(CreateUserRequest createUserRequest);
}
