package kusitms.duduk.domain.user.vo;

import lombok.Getter;

@Getter
public class RefreshToken {

    private String value;

    public void update(String reIssuedRefreshToken) {
        this.value = reIssuedRefreshToken;
    }

    private RefreshToken(String refreshToken) {
        this.value = refreshToken;
    }

    public static RefreshToken of(String refreshToken) {
        return new RefreshToken(refreshToken);
    }
}
