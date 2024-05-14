package kusitms.duduk.application.comment.persistence;

import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import kusitms.duduk.application.newsletter.persistence.NewsLetterJpaMapper;
import kusitms.duduk.application.user.persistence.UserJpaMapper;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.comment.vo.Content;
import kusitms.duduk.domain.global.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
            .build();
    }

    public Comment toDomain(CommentJpaEntity commentJpaEntity) {
        return Comment.builder()
            .id(Id.of(commentJpaEntity.getId()))
            .user(userJpaMapper.toDomain(commentJpaEntity.getUser()))
            .newsLetter(newsLetterJpaMapper.toDomain(commentJpaEntity.getNewsLetter()))
            .content(Content.from(commentJpaEntity.getContent()))
            .createdAt(commentJpaEntity.getCreatedAt())
            .build();
    }
}