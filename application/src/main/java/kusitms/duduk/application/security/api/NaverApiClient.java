package kusitms.duduk.application.security.api;

import kusitms.duduk.core.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.core.security.port.output.OAuthClientPort;
import org.springframework.stereotype.Component;

@Component
public class NaverApiClient implements OAuthClientPort {

    @Override
    public OAuthDetailResponse retrieveOAuthDetail(String accessToken) {
        return null;
    }
}
