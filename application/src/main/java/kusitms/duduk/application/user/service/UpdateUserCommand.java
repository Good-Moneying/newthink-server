package kusitms.duduk.application.user.service;

import kusitms.duduk.core.user.port.input.UpdateUserUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserCommand implements UpdateUserUseCase {

    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;

    public void updateRefreshToken(String email, String refreshToken) {
        loadUserPort.findByEmail(email)
            .ifPresent(user -> {
	user.updateRefreshToken(refreshToken);
	saveUserPort.saveAndFlush(user);
            });
    }
}
