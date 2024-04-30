package kusitms.duduk.security.dto.response;

import lombok.Builder;

public record JwtTokenResponse(String accessToken, String refreshToken) {

    @Builder
    public JwtTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
