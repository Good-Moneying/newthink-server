package kusitms.duduk.core.newsletter.dto.request;

import org.springframework.util.Assert;

public record CreateAiNewsLetterRequest(String thumbnail, String title, String content,
		         String keywords, String category, String summary,
		         String aiType) {

    public CreateAiNewsLetterRequest {
        Assert.notNull(title, "title must not be null");
        Assert.notNull(content, "content must not be null");
        Assert.notNull(keywords, "keywords must not be null");
        Assert.notNull(category, "category must not be null");
        Assert.notNull(summary, "summary must not be null");
        Assert.notNull(aiType, "aiType must not be null");
    }
}