package kusitms.duduk.apiserver.security.infrastructure;

import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoadUserPort loadUserPort;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new CustomUserDetails(user);
    }
}
