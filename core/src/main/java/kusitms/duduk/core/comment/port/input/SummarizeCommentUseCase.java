package kusitms.duduk.core.comment.port.input;


import kusitms.duduk.domain.comment.Comment;

public interface SummarizeCommentUseCase {

    String summarize(Comment comment);
}
