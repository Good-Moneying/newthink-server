package kusitms.duduk.core.security.port.output;

import kusitms.duduk.core.security.dto.response.OAuthDetailResponse;

public interface OAuthClientPort {

    OAuthDetailResponse retrieveOAuthDetail(String accessToken);
}
