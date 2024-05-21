package kusitms.duduk.domain.global;

import java.util.Arrays;
import kusitms.duduk.domain.user.vo.Goal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    GLOBAL("글로벌"),
    FINANCE("금융"),
    SECURITIES("증권"),
    REAL_ESTATE("부동산"),
    COMPANY("기업"),
    TECH("테크"),
    LIFE("라이프"),
    POLICY("정책"),
    WORD("단어장"),
    NONE("없음");

    private final String description;

    public static Category from(String name) {
        return Arrays.stream(Category.values())
            .filter(c -> c.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 카테고리입니다."));
    }
}
