package kusitms.duduk.core.user.port.input;


import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;

public interface RegisterUserUseCase {
    UserResponse register(CreateUserRequest createUserRequest);
}
