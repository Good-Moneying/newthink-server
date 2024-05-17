package kusitms.duduk.core.comment.port.output;

import java.util.List;
import java.util.Optional;
import kusitms.duduk.domain.comment.Comment;

public interface LoadCommentPort {

    Optional<Comment> findById(Long id);
    boolean isExistByNewsLetterIdAndUserId(Long newsLetterId, Long userId);

    List<Comment> findByNewsLetterIdOrderByLikeCountDesc(Long newsLetterId);
}
