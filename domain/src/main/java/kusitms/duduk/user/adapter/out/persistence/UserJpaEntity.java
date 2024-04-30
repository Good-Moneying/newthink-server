package kusitms.duduk.user.adapter.out.persistence;

import io.jsonwebtoken.lang.Assert;
import java.time.LocalDate;
import kusitms.duduk.global.domain.Category;
import kusitms.duduk.security.domain.Provider;
import kusitms.duduk.user.domain.Gender;
import kusitms.duduk.user.domain.Goal;
import kusitms.duduk.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJpaEntity {

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

    public UserJpaEntity(String email, String nickname, LocalDate birthday) {
        this.email = email;
        this.nickname = nickname;
        this.birthday = birthday;
    }
}
