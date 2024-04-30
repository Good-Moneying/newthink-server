package kusitms.duduk.domain.user.application.port.in;

public interface UpdateUserUseCase {
    void updateRefreshToken(String email, String refreshToken);
}
