package kusitms.duduk.core.comment.port.input;

import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;

public interface CreateCommentUseCase {

    CommentResponse create(String email, Long newsLetterId, CreateCommentRequest request);
}
