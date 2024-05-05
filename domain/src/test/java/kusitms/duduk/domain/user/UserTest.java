package kusitms.duduk.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import kusitms.duduk.domain.user.vo.Acorn;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 유저_객체를_생성한다() {
        // given
        String email = "test@test.com";
        String nickname = "test";
        String refreshToken = "1234567890";
        String gender = "MALE";
        LocalDate birthDay = LocalDate.of(1999, 3, 27);
        Role role = Role.USER;
        Provider provider = Provider.from("KAKAO");
        Acorn acorn = Acorn.initial();

        // when
        User user = User.builder()
            .email(Email.of(email))
            .nickname(Nickname.of(nickname))
            .refreshToken(RefreshToken.of(refreshToken))
            .gender(Gender.from(gender))
            .birthday(birthDay)
            .role(role)
            .provider(provider)
            .acorn(acorn)
            .build();

        // then
        assertThat(user.getEmail().getValue()).isEqualTo(email);
        assertThat(user.getNickname().getValue()).isEqualTo(nickname);
        assertThat(user.getRefreshToken().getValue()).isEqualTo(refreshToken);
        assertThat(user.getGender().name()).isEqualTo(gender);
        assertThat(user.getBirthday()).isEqualTo(birthDay);
        assertThat(user.getRole()).isEqualTo(role);
        assertThat(user.getProvider()).isEqualTo(provider);
        assertThat(user.getAcorn()).isEqualTo(acorn);
    }
}
