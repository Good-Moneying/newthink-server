package kusitms.duduk.security.handler;

import kusitms.duduk.security.presentation.dto.OAuthDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuthHandler implements OAuthHandler{

    @Override
    public OAuthDetailResponse retrieveOAuthDetail(String accessToken) {
        return null;
    }
}
