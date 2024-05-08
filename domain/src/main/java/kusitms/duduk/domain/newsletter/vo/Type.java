package kusitms.duduk.domain.newsletter.vo;

import java.util.Arrays;

public enum Type {
    Editor, AI;

    public static Type from(String type) {
        return Arrays.stream(Type.values())
            .filter(t -> t.name().equalsIgnoreCase(type))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 뉴스레터 타입입니다."));
    }
}
