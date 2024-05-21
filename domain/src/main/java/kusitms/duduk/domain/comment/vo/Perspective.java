package kusitms.duduk.domain.comment.vo;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Perspective {
    POSITIVE("긍정"),
    NEGATIVE("부정"),
    UNCERTAIN("잘모르겠음");

    private final String description;

    public static Perspective from(String name) {
        return Arrays.stream(Perspective.values())
            .filter(p -> p.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 관점입니다."));
    }
}
