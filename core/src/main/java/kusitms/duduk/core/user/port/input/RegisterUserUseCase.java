package kusitms.duduk.core.user.port.input;


import kusitms.duduk.core.user.dto.request.CreateUserRequest;

public interface RegisterUserUseCase {
    void register(CreateUserRequest createUserRequest);
}
