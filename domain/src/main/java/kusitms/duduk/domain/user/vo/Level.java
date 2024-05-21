package kusitms.duduk.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
    LV1(100, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L1.jpg"),
    LV2(200, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L2.jpg"),
    LV3(300, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L3.jpg"),
    LV4(400, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L4.jpg"),
    LV5(500, "https://goodmoneying.s3.ap-northeast-2.amazonaws.com/user_character/character_L5.jpg");

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
