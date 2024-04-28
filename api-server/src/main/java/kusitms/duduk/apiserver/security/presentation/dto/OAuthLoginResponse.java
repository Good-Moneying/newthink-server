package kusitms.duduk.apiserver.security.presentation.dto;

import kusitms.duduk.domain.security.domain.Provider;

public record OAuthLoginResponse(String accessToken, Provider provider, boolean isRegistered) {

}
