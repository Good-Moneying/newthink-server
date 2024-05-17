package kusitms.duduk.application.comment.persistence;

import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;

@Mapper
public class CommentJpaMapper {

    public CommentJpaEntity toJpaEntity(Comment comment) {
        return CommentJpaEntity.builder()
            .id(comment.getId() != null ? comment.getId().getValue() : null)
            .userId(comment.getUserId().getValue())
            .newsLetterId(comment.getNewsLetterId().getValue())
            .content(comment.getSentence().getValue())
            .perspective(comment.getPerspective())
            .summarizedContent(null)
            .likeCount(Count.initial().getCount())
            .build();
    }

    public CommentJpaEntity toJpaEntity(Comment comment, CommentJpaEntity persistedComment) {
        return persistedComment.builder()
            .content(comment.getSentence().getValue())
            .summarizedContent(comment.getSummarizedSentence().getValue())
            .likeCount(comment.getLikeCount().getCount())
            .build();
    }

    public Comment toDomain(CommentJpaEntity commentJpaEntity) {
        return Comment.builder()
            .id(Id.of(commentJpaEntity.getId()))
            .userId(Id.of(commentJpaEntity.getUserId()))
            .newsLetterId(Id.of(commentJpaEntity.getNewsLetterId()))
            .sentence(Sentence.from(commentJpaEntity.getContent()))
            .summarizedSentence(Sentence.from(commentJpaEntity.getSummarizedContent()))
            .perspective(commentJpaEntity.getPerspective())
            .likeCount(Count.from(commentJpaEntity.getLikeCount()))
            .createdAt(commentJpaEntity.getCreatedAt())
            .build();
    }
}