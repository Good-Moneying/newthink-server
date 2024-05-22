package kusitms.duduk.application.user.service;

import java.time.LocalDate;
import java.util.ArrayList;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.user.User;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.ExperiencePoint;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;

public class UserSteps {

    public final static String EMAIL = "tester@gmail.com";
    public final static String NICKNAME = "tester";
    private final static String REFRESH_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb3kucGFjay5ndW4ud29vQGdtYWlsLmNvbSIsImlzcyI6ImR1ZHVrLnNob3AiLCJleHAiOjE3MTU5NDQ4MDQsImlhdCI6MTcxNDczNTIwNH0.hOH9QW6MY6RU2Se7T4xqWUGXdbGojaHtsJHdMAMln88";
    private final static String GENDER_TEXT = "MALE";
    private final static LocalDate BIRTHDAY = LocalDate.of(1999, 3, 27);
    private final static Provider PROVIDER = Provider.from("KAKAO");
    private final static Category CATEGORY_FINANCE = Category.FINANCE;
    private final static Goal GOAL_EVERYDAY = Goal.EVERYDAY;

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
                .email(Email.from(email))
                .nickname(Nickname.from("tester"))
                .refreshToken(RefreshToken.of("12345"))
                .goal(Goal.EVERYDAY)
                .gender(Gender.MALE)
                .reward(Count.initial())
                .follower(Count.initial())
                .followee(Count.initial())
                .birthday(LocalDate.of(1990, 1, 1))
                .provider(Provider.KAKAO)
                .experiencePoint(ExperiencePoint.initial())
                .role(Role.EDITOR)
                .archives(new ArrayList<>())
                .comments(new ArrayList<>())
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
                .reward(Count.initial())
                .follower(Count.initial())
                .followee(Count.initial())
                .experiencePoint(ExperiencePoint.initial())
                .archives(new ArrayList<>())
                .comments(new ArrayList<>())
                .category(Category.FINANCE)
                .build();

        return user;
    }

}
