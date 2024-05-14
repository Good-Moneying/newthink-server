package kusitms.duduk.core.comment.dto.request;

import lombok.Builder;

@Builder
public record CreateCommentRequest(String content, String perspective) {

}
