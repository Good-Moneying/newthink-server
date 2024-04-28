package kusitms.duduk.apiserver.security.handler;

import kusitms.duduk.apiserver.security.presentation.dto.OAuthDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class NaverOAuthHandler implements OAuthHandler{

    @Override
    public OAuthDetailResponse retrieveOAuthDetail(String accessToken) {
        return null;
    }
}
