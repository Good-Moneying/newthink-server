package kusitms.duduk.core.security.dto.response;

import kusitms.duduk.domain.user.vo.Provider;

public record OAuthLoginResponse(String accessToken, String refreshToken, Provider provider, boolean isRegistered) {

}
