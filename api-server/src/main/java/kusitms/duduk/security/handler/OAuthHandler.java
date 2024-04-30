package kusitms.duduk.security.handler;

import kusitms.duduk.security.presentation.dto.OAuthDetailResponse;

public interface OAuthHandler {

    OAuthDetailResponse retrieveOAuthDetail(String accessToken);
}
