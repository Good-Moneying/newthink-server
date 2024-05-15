package kusitms.duduk.core.newsletter.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
public record NewsLetterDetailResponse(LocalDate publishedAt, String editor,
		       ArrayList<Block> blocks, ArrayList<Comment> comments,
		       boolean isCommented) {


    @Data
    @AllArgsConstructor
    public static class Block {

        private String type;
        private String content;
    }

    @Data
    @AllArgsConstructor
    public static class Comment {

        private String nickname;
        private String content;
    }
}
