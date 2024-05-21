package kusitms.duduk.core.crawling.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CrawlingNewsResponse {
    private String title;
    private String content;
    private String thumbnailURL;
}
