package kusitms.duduk.core.crawling.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class CrawlingNewsResponse implements Serializable {
    private String title;
    private String content;
    private String thumbnailURL;
}
