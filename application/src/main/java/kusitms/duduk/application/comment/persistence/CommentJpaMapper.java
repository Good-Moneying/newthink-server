package kusitms.duduk.application.comment.persistence;

import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import kusitms.duduk.application.newsletter.persistence.NewsLetterJpaMapper;
import kusitms.duduk.application.user.persistence.UserJpaMapper;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.comment.vo.Content;
import kusitms.duduk.domain.comment.vo.Perspective;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import org.springframework.context.annotation.Lazy;

@Mapper
public class CommentJpaMapper {

    private NewsLetterJpaMapper newsLetterJpaMapper;
    private UserJpaMapper userJpaMapper;

    public CommentJpaMapper(
        @Lazy NewsLetterJpaMapper newsLetterJpaMapper,
        @Lazy UserJpaMapper userJpaMapper) {
        this.newsLetterJpaMapper = newsLetterJpaMapper;
        this.userJpaMapper = userJpaMapper;
    }

    public CommentJpaEntity toJpaEntity(Comment comment) {
        return CommentJpaEntity.builder()
            .id(comment.getId() != null ? comment.getId().getValue() : null)
            .user(userJpaMapper.toJpaEntity(comment.getUser()))
            .newsLetter(newsLetterJpaMapper.toJpaEntity(comment.getNewsLetter()))
            .content(comment.getContent().getSentence())
            .perspective(comment.getPerspective())
            .summarizedContent(null)
            .likeCount(Count.initial().getCount())
            .build();
    }

    public CommentJpaEntity toJpaEntity(Comment comment, CommentJpaEntity persistedComment) {
        return persistedComment.builder()
            .content(comment.getContent().getSentence())
            .summarizedContent(comment.getSummarizedContent().getSentence())
            .likeCount(comment.getLikeCount().getCount())
            .build();
    }

    public Comment toDomain(CommentJpaEntity commentJpaEntity) {
        return Comment.builder()
            .id(Id.of(commentJpaEntity.getId()))
            .user(userJpaMapper.toDomain(commentJpaEntity.getUser()))
            .newsLetter(newsLetterJpaMapper.toDomain(commentJpaEntity.getNewsLetter()))
            .content(Content.from(commentJpaEntity.getContent()))
            .summarizedContent(Content.from(commentJpaEntity.getSummarizedContent()))
            .perspective(commentJpaEntity.getPerspective())
            .likeCount(Count.from(commentJpaEntity.getLikeCount()))
            .createdAt(commentJpaEntity.getCreatedAt())
            .build();
    }
}