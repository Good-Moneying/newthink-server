package kusitms.duduk.user.application.port.in;

public interface UpdateUserUseCase {
    void updateRefreshToken(String email, String refreshToken);
}