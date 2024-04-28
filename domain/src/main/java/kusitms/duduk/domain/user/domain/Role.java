package kusitms.duduk.domain.user.domain;

import java.util.Arrays;

public enum Role {
    USER("ROLE_USER"),
    EDITOR("ROLE_EDITOR"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role from(String role) {
        return Arrays.stream(Role.values())
            .filter(r -> r.getValue().equalsIgnoreCase(role))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 권한입니다."));
    }
}
