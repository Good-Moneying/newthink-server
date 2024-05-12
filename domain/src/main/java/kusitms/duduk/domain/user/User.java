package kusitms.duduk.domain.user;

import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.domain.archive.Archive;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class User {

    private Id id;
    private Email email;
    private Nickname nickname;
    private RefreshToken refreshToken;
    private Gender gender;
    private LocalDate birthday;
    private Role role;
    private Provider provider;
    private Category category;
    private Goal goal;
    private List<Archive> archives;

    public void updateRefreshToken(String reIssuedRefreshToken) {
        this.refreshToken.update(reIssuedRefreshToken);
    }

    public boolean isWritable() {
        return this.role.equals(Role.EDITOR) || this.role.equals(Role.ADMIN);
    }

    public void archiveNewsLetter(NewsLetter newsLetter) {
        this.archives.stream()
            .filter(archive -> archive.getCategory().equals(newsLetter.getCategory()))
            .findFirst()
            .ifPresent(archive -> archive.addNewsLetter(newsLetter.getNewsLetterId()));
    }
}