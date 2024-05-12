package kusitms.duduk.apiserver.user.presentation;

import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserEmailRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserNicknameRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import kusitms.duduk.core.user.port.input.ValidateDuplicatedUserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {

    private final RegisterUserUseCase registerUserUseCase;
    private final ValidateDuplicatedUserQuery validateDuplicatedUserQuery;

    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(registerUserUseCase.register(createUserRequest),
            HttpStatus.CREATED);
    }

    @PostMapping("/validate/email")
    public ResponseEntity<Void> validateEmail(@RequestBody ValidateUserEmailRequest request) {
        validateDuplicatedUserQuery.validateDuplicatedEmail(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/nickname")
    public ResponseEntity<Void> validateNickname(@RequestBody ValidateUserNicknameRequest request) {
        validateDuplicatedUserQuery.validateDuplicatedNickname(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@AuthenticationPrincipal
    CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(customUserDetails.getEmail()
            , HttpStatus.OK);
    }


}
