package kusitms.duduk.user.adapter.in.web;

import kusitms.duduk.user.dto.request.CreateUserRequest;
import kusitms.duduk.user.port.in.RegisterUserUseCase;
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
    public ResponseEntity<Void> register(@RequestBody CreateUserRequest createUserRequest) {
        registerUserUseCase.register(createUserRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
