package kusitms.duduk.domain.user;

import java.time.LocalDate;
import kusitms.duduk.domain.global.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String email;
    private String nickname;
    private String refreshToken;
    private Gender gender;
    private LocalDate birthday;
    private Role role;
    private Provider provider;
    private Category category;
    private Goal goal;
    private int acornCount;

    public void updateRefreshToken(String reIssuedRefreshToken) {
        this.refreshToken = reIssuedRefreshToken;
    }

    private User(String email, String nickname, LocalDate birthday) {
        this.email = email;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public static User create(String email, String nickname, LocalDate localDate) {
        return new User(email, nickname, localDate);
    }
}