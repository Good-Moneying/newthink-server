package kusitms.duduk.core.newsletter.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

// todo: 뉴스레터의 성경에 따라 keywords 혹은 category가 null이 될 수 있습니다.
@Builder(toBuilder = true)
public record NewsLetterThumbnailResponse(String thumbnail, String title, LocalDateTime createdAt,
		          String keywords, String category, boolean isScrapped) {

}


