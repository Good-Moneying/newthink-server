package kusitms.duduk.security.port.out;

import kusitms.duduk.security.dto.response.OAuthDetailResponse;

public interface OAuthClientPort {
    OAuthDetailResponse retrieveOAuthDetail(String accessToken);
}
