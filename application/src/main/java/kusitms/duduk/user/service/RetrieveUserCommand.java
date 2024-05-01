package kusitms.duduk.user.service;

import kusitms.duduk.user.port.in.RetrieveUserQuery;
import kusitms.duduk.user.port.out.LoadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RetrieveUserCommand implements RetrieveUserQuery {
    private final LoadUserPort loadUserPort;
    @Override
    public boolean isUserRegisteredByEmail(String email) {
        return loadUserPort.existsByEmail(email);
    }
}
