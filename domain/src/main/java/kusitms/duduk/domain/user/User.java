package kusitms.duduk.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import kusitms.duduk.domain.archive.Archive;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.ExperiencePoint;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Level;
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
    private ExperiencePoint experiencePoint;
    private Level level;
    private Goal goal;
    private Count reward;
    private Count follower;
    private Count followee;
    private List<Archive> archives;
    private List<Comment> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
            .ifPresent(archive -> archive.addNewsLetter(newsLetter.getId()));
    }

    public void archiveNewsLetterWithCategory(NewsLetter newsLetter, String category) {
        this.archives.stream()
            .filter(archive -> archive.getCategory().name().equalsIgnoreCase(category))
            .findFirst()
            .ifPresent(archive -> archive.addNewsLetter(newsLetter.getId()));
    }

    public void archiveTerm(Term term) {
        this.archives.stream()
            .filter(archive -> archive.getCategory().equals(Category.WORD))
            .findFirst()
            .ifPresent(archive -> archive.addTerm(term.getId()));
    }

    public boolean isScrapped(NewsLetter newsLetter) {
        return this.archives.stream()
            .filter(archive -> archive.getNewsLetterIds()
	.contains(newsLetter.getId().getValue()))
            .findFirst()
            .isPresent();
    }

    public boolean isScrapped(Term term) {
        return this.archives.stream()
            .filter(archive -> archive.getTermIds().contains(term.getId().getValue()))
            .findFirst()
            .isPresent();
    }

    public List<Long> getNewsLettersFromArchive(Category category) {
        return this.archives.stream()
            .filter(archive -> archive.getCategory().equals(category))
            .findFirst()
            .map(Archive::getNewsLetterIds)
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public List<Long> getTermsFromArchive() {
        return this.archives.stream()
            .filter(archive -> archive.getCategory().equals(Category.WORD))
            .findFirst()
            .map(Archive::getTermIds)
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public void addComment(Comment savedComment) {
        this.comments.add(savedComment);
    }


    public void addReward(int rewardAmount) {
        this.reward.addAmount(rewardAmount);
    }
}