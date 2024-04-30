package kusitms.duduk.security.dto.response;

import kusitms.duduk.security.domain.Provider;

public record OAuthLoginResponse(String accessToken, String refreshToken, Provider provider, boolean isRegistered) {

}
