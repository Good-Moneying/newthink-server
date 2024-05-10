package kusitms.duduk.domain.user.service;

import kusitms.duduk.common.annotation.DomainService;
import kusitms.duduk.domain.user.User;

@DomainService
public class UpdateUserDomainService {

    public void updateRefreshToken(User user, String refreshToken) {
        user.updateRefreshToken(refreshToken);
    }
}
