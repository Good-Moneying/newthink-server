package kusitms.duduk.user.port.in;

public interface UpdateUserUseCase {
    void updateRefreshToken(String email, String refreshToken);
}
