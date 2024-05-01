package kusitms.duduk.user;

import java.time.LocalDate;
import kusitms.duduk.global.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

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
        Assert.notNull(reIssuedRefreshToken, "RefreshToken must not be null");
        this.refreshToken = reIssuedRefreshToken;
    }

    public User(String email, String nickname, LocalDate birthday) {
        this.email = email;
        this.nickname = nickname;
        this.birthday = birthday;
    }
}