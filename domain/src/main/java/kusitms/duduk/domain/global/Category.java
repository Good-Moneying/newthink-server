package kusitms.duduk.domain.global;

import java.util.Arrays;
import kusitms.duduk.domain.user.vo.Goal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    STOCK("주식"),
    CRYPTO("암호화폐"),
    ESTATE("부동산"),
    FUND("펀드"),
    ETC("기타");

    private final String description;

    public static Category from(String description) {
        return Arrays.stream(Category.values())
            .filter(d -> d.description.equals(description))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 카테고리입니다."));
    }
}
