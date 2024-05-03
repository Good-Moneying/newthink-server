package kusitms.duduk.core.user.port.input;

public interface UpdateUserUseCase {
    void updateRefreshToken(String email, String refreshToken);
}
