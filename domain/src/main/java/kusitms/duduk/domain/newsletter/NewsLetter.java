package kusitms.duduk.domain.newsletter;

import java.time.LocalDateTime;
import java.util.List;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.global.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import kusitms.duduk.domain.newsletter.vo.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@ToString
public class NewsLetter {

    private Id id;
    private Id editorId;
    private Thumbnail thumbnail;
    private Title title;
    private Content content;
    private Keywords keywords;
    private Category category;
    private Type type;
    private Summary summary;
    private List<Comment> comments;
    private Count viewCount;
    private Count scrapCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean isWrittenBy(Id id) {
        return editorId.equals(id);
    }

    public void increaseViewCount() {
        this.viewCount.increase();
    }

    public void increaseScrapCount() {
        this.scrapCount.increase();
    }

    public void addComment(Comment savedComment) {
        this.comments.add(savedComment);
    }

    public void addSummary(String summary) {
        this.summary = Summary.from(summary);
    }
}
