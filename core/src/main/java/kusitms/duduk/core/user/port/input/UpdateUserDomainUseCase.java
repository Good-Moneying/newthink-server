package kusitms.duduk.core.user.port.input;

import kusitms.duduk.domain.user.User;

public interface UpdateUserDomainUseCase {

    void updateRefreshToken(User user, String refreshToken);
}
