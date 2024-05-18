package kusitms.duduk.core.newsletter.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import kusitms.duduk.domain.newsletter.vo.Summary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder(toBuilder = true)
public record NewsLetterDetailResponse(String title,
		       Editor editor,
		       LocalDateTime publishedAt,
                                       String summary,
		       List<Block> body,
		       List<Comment> comments,
		       boolean isCommented) {

    @Data
    @AllArgsConstructor
    @Builder
    public static class Editor {

        private String nickname;
        private String profileUrl;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class Block {

        private String type;
        private String content;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class Comment {

        private Long userId;
        private String content;
        private int likeCount;
    }
}
