package kusitms.duduk.domain.archive;

import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(toBuilder = true)
public class Archive {

    private Long id;
    private Category category;
    private List<Long> termIds = new ArrayList<>();

    public static Archive create(Long id, Category category, List<Long> termIds) {
        return Archive.builder()
            .id(id)
            .category(category)
            .termIds(termIds)
            .build();
    }

    public void addTermId(Long termId) {
        this.termIds.add(termId);
    }

    public void removeTermId(Long termId) {
        this.termIds.remove(termId);
    }
}

