package kusitms.duduk.application.user.service;

import kusitms.duduk.core.user.port.input.RetrieveUserQuery;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RetrieveUserCommand implements RetrieveUserQuery {
    private final LoadUserPort loadUserPort;
    @Override
    public boolean isUserRegisteredByEmail(String email) {
        return loadUserPort.existsUserByEmail(email);
    }
}
