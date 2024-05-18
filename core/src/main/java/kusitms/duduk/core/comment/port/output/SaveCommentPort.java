package kusitms.duduk.core.comment.port.output;

import kusitms.duduk.domain.comment.Comment;

public interface SaveCommentPort {

    Comment save(Comment comment);
}
