package kusitms.duduk.apiserver.security.presentation.dto;

import kusitms.duduk.domain.security.domain.Provider;

public record OAuthLoginResponse(String accessToken, String refreshToken, Provider provider, boolean isRegistered) {

}
