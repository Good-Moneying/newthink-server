package kusitms.duduk.user.adapter.out.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import kusitms.duduk.global.BaseEntity;
import kusitms.duduk.global.Category;
import kusitms.duduk.user.Gender;
import kusitms.duduk.user.Goal;
import kusitms.duduk.user.Provider;
import kusitms.duduk.user.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class UserJpaEntity extends BaseEntity {

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
        io.jsonwebtoken.lang.Assert.notNull(reIssuedRefreshToken, "RefreshToken must not be null");
        this.refreshToken = reIssuedRefreshToken;
    }

    private UserJpaEntity(String email, String nickname, LocalDate birthday) {
        this.email = email;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public static UserJpaEntity create(String email, String nickname,
        LocalDate birthday) {
        return new UserJpaEntity(email, nickname, birthday);
    }
}
