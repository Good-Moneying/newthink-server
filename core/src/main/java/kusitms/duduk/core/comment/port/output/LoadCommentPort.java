package kusitms.duduk.core.comment.port.output;

import java.util.Optional;
import kusitms.duduk.domain.comment.Comment;

public interface LoadCommentPort {

    Optional<Comment> findById(Long id);
}
