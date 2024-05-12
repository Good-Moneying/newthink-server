package kusitms.duduk.application.user.service;

import static kusitms.duduk.domain.global.TestProperties.BIRTHDAY;
import static kusitms.duduk.domain.global.TestProperties.CATEGORY_FINANCE;
import static kusitms.duduk.domain.global.TestProperties.EMAIL;
import static kusitms.duduk.domain.global.TestProperties.GENDER_TEXT;
import static kusitms.duduk.domain.global.TestProperties.GOAL_EVERYDAY;
import static kusitms.duduk.domain.global.TestProperties.NICKNAME;
import static kusitms.duduk.domain.global.TestProperties.PROVIDER;
import static kusitms.duduk.domain.global.TestProperties.REFRESH_TOKEN;

import java.time.LocalDate;
import java.util.ArrayList;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.user.User;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;

public class UserSteps {

    public static CreateUserRequest createUserRequest_생성() {
        return new CreateUserRequest(
            EMAIL,
            NICKNAME,
            REFRESH_TOKEN,
            GENDER_TEXT,
            BIRTHDAY,
            PROVIDER.name(),
            CATEGORY_FINANCE.name(),
            GOAL_EVERYDAY.name()
        );
    }

    public static User ROLE_EDITOR_생성_요청() {
        String email = "test@test.com";

        User user = User.builder()
//            .id(Id.of(1L))
            .email(Email.from(email))
            .nickname(Nickname.from("tester"))
            .refreshToken(RefreshToken.of("12345"))
            .goal(Goal.EVERYDAY)
            .gender(Gender.MALE)
            .birthday(LocalDate.of(1990, 1, 1))
            .provider(Provider.KAKAO)
            .role(Role.EDITOR)
            .archives(new ArrayList<>())
            .build();

        return user;
    }

    public static User ROLE_USER_생성_요청() {
        String email = "test1@test.com";

        User user = User.builder()
            .email(Email.from(email))
            .nickname(Nickname.from("tester1"))
            .refreshToken(RefreshToken.of("123456"))
            .goal(Goal.EVERYDAY)
            .gender(Gender.MALE)
            .birthday(LocalDate.of(1990, 1, 1))
            .provider(Provider.KAKAO)
            .role(Role.USER)
            .archives(new ArrayList<>())
            .category(Category.FINANCE)
            .build();

        return user;
    }

}
