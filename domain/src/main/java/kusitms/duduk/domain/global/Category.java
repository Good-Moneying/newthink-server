package kusitms.duduk.domain.global;

import java.util.Arrays;
import kusitms.duduk.domain.user.vo.Goal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    GLOBAL("글로벌" , "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/global.png"),
    FINANCE("금융", "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/finance.png"),
    SECURITIES("증권", "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/securities.png"),
    REAL_ESTATE("부동산" , "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/real_estate.png"),
    COMPANY("기업" , "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/company.png"),
    TECH("테크" , "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/tech.png"),
    LIFE("라이프" , "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/life.png"),
    POLICY("정책" , "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/category_logo/policy.png"),
    WORD("단어장" , "NONE"),
    NONE("없음", "NONE");

    private final String description;
    private final String logoUrl;

    public static Category from(String name) {
        return Arrays.stream(Category.values())
            .filter(c -> c.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 카테고리입니다."));
    }
}
