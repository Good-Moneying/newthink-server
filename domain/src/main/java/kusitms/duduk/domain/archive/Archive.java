package kusitms.duduk.domain.archive;

import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(toBuilder = true)
public class Archive {

    // todo : Id VO 객체로 바꾸자
    private Long id;
    private Category category;
    @Default
    private List<Long> newsLetterIds = new ArrayList<>();
    @Default
    private List<Long> termIds = new ArrayList<>();

    public static Archive create(Long id, Category category, List<Long> newsLetterIds,
        List<Long> termIds) {
        return Archive.builder()
            .id(id)
            .category(category)
            .newsLetterIds(newsLetterIds)
            .termIds(termIds)
            .build();
    }

    public void addNewsLetter(Id newsLetterId) {
        this.newsLetterIds.add(newsLetterId.getValue());
    }

    public void addTerm(Id id) {
        this.termIds.add(id.getValue());
    }
}