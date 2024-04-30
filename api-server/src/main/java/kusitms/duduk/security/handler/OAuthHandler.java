package kusitms.duduk.security.handler;

import kusitms.duduk.security.dto.response.OAuthDetailResponse;

public interface OAuthHandler {

    OAuthDetailResponse retrieveOAuthDetail(String accessToken);
}
