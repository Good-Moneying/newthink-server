package kusitms.duduk.application.user.service;

import java.time.LocalDate;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.user.User;
import kusitms.duduk.domain.user.vo.Acorn;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;

public class UserSteps {
    public static User ROLE_EDITOR_생성_요청() {
        String email = "test@test.com";

        User user = User.builder()
            .id(Id.of(1L))
            .email(Email.of(email))
            .nickname(Nickname.of("tester"))
            .refreshToken(RefreshToken.of("12345"))
            .goal(Goal.EVERYDAY)
            .acorn(Acorn.initial())
            .gender(Gender.MALE)
            .birthday(LocalDate.of(1990, 1, 1))
            .provider(Provider.KAKAO)
            .role(Role.EDITOR)
            .build();

        return user;
    }

    public static User ROLE_USER_생성_요청() {
        String email = "test1@test.com";

        User user = User.builder()
            .email(Email.of(email))
            .nickname(Nickname.of("tester1"))
            .refreshToken(RefreshToken.of("123456"))
            .goal(Goal.EVERYDAY)
            .acorn(Acorn.initial())
            .gender(Gender.MALE)
            .birthday(LocalDate.of(1990, 1, 1))
            .provider(Provider.KAKAO)
            .role(Role.USER)
            .build();

        return user;
    }

}
