package kusitms.duduk.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
    LV1(1000, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L1.jpg"),
    LV2(2000, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L2.jpg"),
    LV3(3000, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L3.jpg"),
    LV4(4000, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L4.jpg"),
    LV5(10000, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L5.jpg");

    private final int experiencePoint;
    private final String profileUrl;

    public static Level of(int experiencePoint) {
        if (experiencePoint < LV1.experiencePoint) {
            return LV1;
        } else if (experiencePoint < LV2.experiencePoint) {
            return LV2;
        } else if (experiencePoint < LV3.experiencePoint) {
            return LV3;
        } else if (experiencePoint < LV4.experiencePoint) {
            return LV4;
        } else {
            return LV5;
        }
    }
}
