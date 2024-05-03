package kusitms.duduk.core.security.dto.response;

public record JwtTokenResponse(String accessToken, String refreshToken) {

    public JwtTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
