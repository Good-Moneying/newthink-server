package kusitms.duduk.apiserver.security.infrastructure;

import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final LoadUserPort loadUserPort;

    @Transactional
    public void verifyAndAuthenticate(String email) {
        loadUserPort.findByEmail(email)
            .ifPresent(this::authenticate);
    }

    private void authenticate(User user) {
        CustomUserDetails userDetails = new CustomUserDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}