package kusitms.duduk.domain.user;

import static kusitms.duduk.domain.global.TestProperties.*;
import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.RefreshToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User 테스트")
public class UserTest {

    @Test
    void 유저_객체를_생성한다() {
        // when
        User user = User.builder()
            .email(Email.of(EMAIL))
            .nickname(Nickname.of(NICKNAME))
            .refreshToken(RefreshToken.of(REFRESH_TOKEN))
            .gender(Gender.from(GENDER_TEXT))
            .birthday(BIRTHDAY)
            .role(ROLE)
            .provider(PROVIDER)
            .category(CATEGORY_STOCK)
            .goal(GOAL_EVERYDAY)
            .acorn(ACORN_INITIAL)
            .build();

        // then
        assertThat(user.getEmail().getValue()).isEqualTo(EMAIL);
        assertThat(user.getNickname().getValue()).isEqualTo(NICKNAME);
        assertThat(user.getRefreshToken().getValue()).isEqualTo(REFRESH_TOKEN);
        assertThat(user.getGender().name()).isEqualTo(GENDER_TEXT);
        assertThat(user.getBirthday()).isEqualTo(BIRTHDAY);
        assertThat(user.getRole()).isEqualTo(ROLE);
        assertThat(user.getProvider()).isEqualTo(PROVIDER);
        assertThat(user.getAcorn()).isEqualTo(ACORN_INITIAL);
    }
}
