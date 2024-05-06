package kusitms.duduk.apiserver.user.presentation;

import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(registerUserUseCase.register(createUserRequest),
            HttpStatus.CREATED);
    }
}
