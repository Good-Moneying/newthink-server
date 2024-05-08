package kusitms.duduk.domain.newsletter;

import java.util.List;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.newsletter.vo.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import kusitms.duduk.domain.newsletter.vo.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class NewsLetter {

    private Id newsLetterId;
    private Id editorId;
    private Thumbnail thumbnail;
    private Title title;
    private Content content;
    private Keywords keywords;
    private Category category;
    private Type type;
    private Summary summary;
    private Count viewCount;
    private Count scrapCount;
}
