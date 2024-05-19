package kusitms.duduk.domain.term.vo;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum TermCategory {
    FINANCE("금융"),
    ECONOMY("경제"),
    COMPANY("기업");

    private final String description;

    TermCategory(String description) {
        this.description = description;
    }

    public static TermCategory from(String name) {
        return Arrays.stream(TermCategory.values())
            .filter(t -> t.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 카테고리입니다."));
    }
}
