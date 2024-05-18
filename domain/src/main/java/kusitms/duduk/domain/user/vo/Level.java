package kusitms.duduk.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
    LV1(100, "https://duduk.kusitms.club/images/level1.png"),
    LV2(200, "https://duduk.kusitms.club/images/level2.png"),
    LV3(300, "https://duduk.kusitms.club/images/level3.png"),
    LV4(400, "https://duduk.kusitms.club/images/level4.png"),
    LV5(500, "https://duduk.kusitms.club/images/level5.png");

    private final int experiencePoint;
    private final String profileUrl;

    public static Level of(int experiencePoint) {
        if (experiencePoint < 100) {
            return LV1;
        } else if (experiencePoint < 200) {
            return LV2;
        } else if (experiencePoint < 300) {
            return LV3;
        } else if (experiencePoint < 400) {
            return LV4;
        } else {
            return LV5;
        }
    }
}
