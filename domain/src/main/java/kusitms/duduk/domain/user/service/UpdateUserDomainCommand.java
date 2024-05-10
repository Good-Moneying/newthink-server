package kusitms.duduk.domain.user.service;

import kusitms.duduk.core.annotation.DomainService;
import kusitms.duduk.core.user.port.input.UpdateUserDomainUseCase;
import kusitms.duduk.domain.user.User;

@DomainService
public class UpdateUserDomainCommand implements UpdateUserDomainUseCase {

    @Override
    public void updateRefreshToken(User user, String refreshToken) {
        user.updateRefreshToken(refreshToken);
    }
}
