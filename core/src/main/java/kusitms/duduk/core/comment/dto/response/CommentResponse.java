package kusitms.duduk.core.comment.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record CommentResponse(Long commentId, Long userId, Long newsLetterId, String content, String perspective, boolean isPrivate, String createdDate, String updatedDate) {

}
