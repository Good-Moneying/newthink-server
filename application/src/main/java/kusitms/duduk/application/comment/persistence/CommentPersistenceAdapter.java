package kusitms.duduk.application.comment.persistence;

import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.comment.port.output.SaveCommentPort;
import kusitms.duduk.core.comment.port.output.DeleteCommentPort;
import kusitms.duduk.core.comment.port.output.LoadCommentPort;
import kusitms.duduk.core.comment.port.output.UpdateCommentPort;
import kusitms.duduk.domain.comment.Comment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class CommentPersistenceAdapter implements SaveCommentPort, DeleteCommentPort,
    LoadCommentPort, UpdateCommentPort {

    private final CommentRepository commentRepository;
    private final CommentJpaMapper commentJpaMapper;

    @Override
    public Comment save(Comment comment) {
        CommentJpaEntity commentJpaEntity = commentJpaMapper.toJpaEntity(comment);
        CommentJpaEntity savedComment = commentRepository.save(commentJpaEntity);
        return commentJpaMapper.toDomain(savedComment);
    }
}
