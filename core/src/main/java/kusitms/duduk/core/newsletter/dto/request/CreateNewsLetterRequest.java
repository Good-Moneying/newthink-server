package kusitms.duduk.core.newsletter.dto.request;

import org.springframework.util.Assert;

public record CreateNewsLetterRequest(String thumbnail, String title, String content,
		      String keywords, String category, String summary, String type) {

    public CreateNewsLetterRequest {
        Assert.notNull(title, "title must not be null");
        Assert.notNull(content, "content must not be null");
        Assert.notNull(keywords, "keywords must not be null");
        Assert.notNull(category, "category must not be null");
        Assert.notNull(summary, "summary must not be null");
    }
}