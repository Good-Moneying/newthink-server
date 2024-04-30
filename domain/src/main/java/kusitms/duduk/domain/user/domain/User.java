package kusitms.duduk.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.jsonwebtoken.lang.Assert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import kusitms.duduk.domain.global.domain.BaseEntity;
import kusitms.duduk.domain.global.domain.Category;
import kusitms.duduk.domain.security.domain.Provider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String nickname;

    @Column
    private String refreshToken;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate birthday;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column
    private int acornCount;

    public void updateRefreshToken(String reIssuedRefreshToken) {
        Assert.notNull(reIssuedRefreshToken, "RefreshToken must not be null");
        this.refreshToken = reIssuedRefreshToken;
    }

    private User(String email, String nickname, LocalDate birthday) {
        this.email = email;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public static User create(String email, String nickname, LocalDate birthday) {
        return new User(email, nickname, birthday);
    }
}
