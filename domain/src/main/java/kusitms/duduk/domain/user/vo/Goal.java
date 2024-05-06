package kusitms.duduk.domain.user.vo;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Goal {
    EVERYDAY("매일"),
    ONE_TO_TWO("일주일에 1 ~ 2번"),
    THREE_TO_FOUR("일주일에 3 ~ 4번"),
    FIVE_TO_SIX("일주일에 5 ~ 6번");

    private final String description;

    public static Goal from(String description) {
        return Arrays.stream(Goal.values())
            .filter(g -> g.name().equalsIgnoreCase(description))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 목표입니다."));
    }
}
