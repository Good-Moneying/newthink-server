package kusitms.duduk.domain.security.jwt;

import lombok.Builder;

public record JwtTokenInfo(String accessToken, String refreshToken) {

    @Builder
    public JwtTokenInfo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
