package kusitms.duduk.application.comment.persistence;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id)
            .map(commentJpaMapper::toDomain);
    }

    @Override
    public boolean isExistByNewsLetterIdAndUserId(Long newsLetterId, Long userId) {
        return commentRepository.existsByNewsLetterIdAndUserId(newsLetterId, userId);
    }

    @Override
    public List<Comment> findByNewsLetterIdOrderByLikeCountDesc(Long newsLetterId) {
        return commentRepository.findByNewsLetterIdOrderByLikeCountDesc(newsLetterId)
            .stream()
            .map(commentJpaMapper::toDomain)
            .toList();
    }

    @Override
    public Optional<Comment> update(Comment comment) {
        Long commentId = comment.getId().getValue();

        return commentRepository.findById(commentId)
            .map(
	persistedCommentData -> commentJpaMapper.toJpaEntity(comment, persistedCommentData))
            .map(commentRepository::save)
            .map(commentJpaMapper::toDomain);
    }


}
