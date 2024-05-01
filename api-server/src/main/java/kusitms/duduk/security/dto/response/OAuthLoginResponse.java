package kusitms.duduk.security.dto.response;

import kusitms.duduk.user.Provider;

public record OAuthLoginResponse(String accessToken, String refreshToken, Provider provider, boolean isRegistered) {

}
