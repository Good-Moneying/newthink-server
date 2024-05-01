package kusitms.duduk.security.adapter.out.api;

import kusitms.duduk.security.dto.response.OAuthDetailResponse;
import kusitms.duduk.security.port.out.OAuthClientPort;
import org.springframework.stereotype.Component;

@Component
public class GoogleApiClient implements OAuthClientPort {

    @Override
    public OAuthDetailResponse retrieveOAuthDetail(String accessToken) {
        return null;
    }
}
