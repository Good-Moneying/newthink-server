package kusitms.duduk.core.comment.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record CommentResponse(Long commentId, String email, Long newsLetterId, String content, String perspective, String createdDate, String updatedDate) {

}
