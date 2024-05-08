package kusitms.duduk.apiserver.security.presentation;

import kusitms.duduk.core.security.dto.response.OAuthLoginResponse;
import kusitms.duduk.core.security.port.input.LoginOAuthUseCase;
import kusitms.duduk.domain.user.vo.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping( "/api/oauth")
public class OAuthController implements OAuthControllerDocs {

    private final LoginOAuthUseCase loginOAuthUseCase;

    @GetMapping("/{provider}")
    public ResponseEntity<OAuthLoginResponse> oAuthLogin(
        @RequestParam(name = "prodiver") final String provider,
        @RequestHeader("OAuth") final String accessToken) {
        return new ResponseEntity<>(loginOAuthUseCase.process(Provider.from(provider), accessToken),
            HttpStatus.OK);
    }
}