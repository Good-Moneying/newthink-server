package kusitms.duduk.batch.dto.crawling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CrawlingNews {
    private String title;
    private String content;
    private String thumbnailURL;
}
