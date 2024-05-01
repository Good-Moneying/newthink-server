package kusitms.duduk.user.service;

import kusitms.duduk.user.port.in.UpdateUserUseCase;
import kusitms.duduk.user.port.out.LoadUserPort;
import kusitms.duduk.user.port.out.SaveUserPort;
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
