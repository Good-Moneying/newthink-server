package kusitms.duduk.domain.user;

import java.util.Arrays;

public enum Provider {
    KAKAO, NAVER, GOOGLE;

    public static Provider from(String provider) {
        return Arrays.stream(Provider.values())
            .filter(p -> p.name().equalsIgnoreCase(provider))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 OAuth 제공자입니다."));
    }
}
