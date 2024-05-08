package kusitms.duduk.domain.global;

import java.util.Arrays;
import kusitms.duduk.domain.user.vo.Goal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    STOCK("stock"),
    CRYPTO("crypto"),
    ESTATE("estate"),
    FUND("fund"),
    ETC("etc");

    private final String description;

    public static Category from(String description) {
        return Arrays.stream(Category.values())
            .filter(d -> d.description.equalsIgnoreCase(description))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 카테고리입니다."));
    }
}
