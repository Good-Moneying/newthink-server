package kusitms.duduk.domain.user.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ExperiencePoint {
    private static final int ZERO = 0;
    private final int value;

    public static ExperiencePoint of(int value) {
        return new ExperiencePoint(value);
    }

    public static ExperiencePoint initial() {
        return new ExperiencePoint(ZERO);
    }
}
