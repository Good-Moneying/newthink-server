package kusitms.duduk.application.newsletter.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.application.comment.persistence.CommentJpaMapper;
import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.global.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Mapper
@Slf4j
public class NewsLetterJpaMapper {

    private final CommentJpaMapper commentJpaMapper;

    public NewsLetterJpaEntity toJpaEntity(NewsLetter newsLetter) {
        if (newsLetter == null) {
            return null;
        }

        return NewsLetterJpaEntity.builder()
            .id(newsLetter.getId() != null ? newsLetter.getId().getValue()
	: null)
            .editorId(newsLetter.getEditorId() != null ? newsLetter.getEditorId().getValue() : null)
            .thumbnail(
	newsLetter.getThumbnail() != null ? newsLetter.getThumbnail().getUrl() : null)
            .title(newsLetter.getTitle().getTitle())
            .content(newsLetter.getContent() != null ? newsLetter.getContent().getContent() : null)
            .comments(newsLetter.getComments() == null ? new ArrayList<>()
	: getCommentJpaEntities(newsLetter))
            .keywords(newsLetter.getKeywords().toSentence())
            .category(newsLetter.getCategory())
            .type(newsLetter.getType())
            .summary(newsLetter.getSummary().toSentence())
            .viewCount(newsLetter.getViewCount().getCount())
            .scrapCount(newsLetter.getScrapCount().getCount())
            .build();
    }

    private List<CommentJpaEntity> getCommentJpaEntities(NewsLetter newsLetter) {
        return newsLetter.getComments().stream()
            .map(comment -> commentJpaMapper.toJpaEntity(comment))
            .collect(Collectors.toCollection(ArrayList::new)); // 변경 가능한 리스트 반환
    }

    public NewsLetterJpaEntity toJpaEntity(NewsLetter newsLetter,
        NewsLetterJpaEntity persistedNewsLetter) {
        if (newsLetter == null) {
            return null;
        }

        return persistedNewsLetter.toBuilder()
            .thumbnail(newsLetter.getThumbnail().getUrl())
            .title(newsLetter.getTitle().getTitle())
            .content(newsLetter.getContent().getContent())
            .keywords(newsLetter.getKeywords().toSentence())
            .category(newsLetter.getCategory())
            .summary(newsLetter.getSummary().toSentence())
            .comments(getCommentJpaEntities(newsLetter))
            .viewCount(newsLetter.getViewCount().getCount())
            .scrapCount(newsLetter.getScrapCount().getCount())
            .build();
    }

    public NewsLetter toDomain(NewsLetterJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        return NewsLetter.builder()
            .id(Id.of(jpaEntity.getId()))
            .editorId(Id.of(jpaEntity.getEditorId()))
            .thumbnail(Thumbnail.from(jpaEntity.getThumbnail()))
            .title(Title.from(jpaEntity.getTitle()))
            .content(Content.from(jpaEntity.getContent()))
            .keywords(Keywords.from(jpaEntity.getKeywords()))
            .category(jpaEntity.getCategory())
            .type(jpaEntity.getType())
            .summary(Summary.from(jpaEntity.getSummary()))
            .viewCount(Count.from(jpaEntity.getViewCount()))
            .scrapCount(Count.from(jpaEntity.getScrapCount()))
            .comments(mapComments(jpaEntity.getComments()))
            .createdAt(jpaEntity.getCreatedAt())
            .updatedAt(jpaEntity.getUpdatedAt())
            .build();
    }

    public List<NewsLetter> toDomainList(List<NewsLetterJpaEntity> newsLetters) {
        return newsLetters.stream()
            .map(this::toDomain)
            .collect(Collectors.toCollection(ArrayList::new)); // 변경 가능한 리스트 반환
    }

    // todo 중복되는데 나중에 분리하자
    private List<Comment> mapComments(List<CommentJpaEntity> comments) {
        return comments.stream()
            .map(commentJpaEntity -> commentJpaMapper.toDomain(commentJpaEntity))
            .collect(Collectors.toCollection(ArrayList::new)); // 변경 가능한 리스트 반환
    }
}
