package kusitms.duduk.application.user.service;

import kusitms.duduk.core.user.port.input.UpdateUserUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.user.service.UpdateUserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserCommand implements UpdateUserUseCase {

    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final UpdateUserDomainService userDomainService;

    public void updateRefreshToken(String email, String refreshToken) {
        loadUserPort.findByEmail(email)
            .ifPresent(user -> {
	// todo : 참조 값이 잘 넘어가는지 확인해야 한다
	userDomainService.updateRefreshToken(user, refreshToken);
	saveUserPort.saveAndFlush(user);
            });
    }
}
