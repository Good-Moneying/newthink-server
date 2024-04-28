package kusitms.duduk.apiserver.security.handler;

import kusitms.duduk.apiserver.security.presentation.dto.OAuthDetailResponse;

public interface OAuthHandler {

    OAuthDetailResponse retrieveOAuthDetail(String accessToken);
}
