package kusitms.duduk.domain.global;

import java.time.LocalDate;
import kusitms.duduk.domain.user.vo.Acorn;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.Role;

// todo : 다른 패키지로 리팩토링 혹은 삭제 예정
public class TestProperties {

    // Create User Request
    public final static String EMAIL = "tester@gmail.com";
    public final static String NICKNAME = "tester";
    public final static String REFRESH_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb3kucGFjay5ndW4ud29vQGdtYWlsLmNvbSIsImlzcyI6ImR1ZHVrLnNob3AiLCJleHAiOjE3MTU5NDQ4MDQsImlhdCI6MTcxNDczNTIwNH0.hOH9QW6MY6RU2Se7T4xqWUGXdbGojaHtsJHdMAMln88";
    private final static Gender GENDER = Gender.MALE;
    public final static String GENDER_TEXT = "MALE";
    public final static LocalDate BIRTHDAY = LocalDate.of(1999, 3, 27);
    public final static Role ROLE = Role.USER;
    public final static Provider PROVIDER = Provider.from("KAKAO");
    public final static Acorn ACORN_INITIAL = Acorn.initial();
    public final static Category CATEGORY_STOCK = Category.STOCK;
    public final static Goal GOAL_EVERYDAY = Goal.EVERYDAY;

}
