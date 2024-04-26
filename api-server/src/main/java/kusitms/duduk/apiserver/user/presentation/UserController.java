package kusitms.duduk.apiserver.user.presentation;

import kusitms.duduk.apiserver.user.presentation.dto.OAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserControllerDocs {

    @GetMapping("/oauth/{provider}")
    public ResponseEntity<OAuthResponse> oAuthLogin() {
        return new ResponseEntity<>(new OAuthResponse(null, null), HttpStatus.OK);
    }
}
