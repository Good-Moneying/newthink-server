package kusitms.duduk.application.newsletter.persistence;

import java.util.List;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.newsletter.vo.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;

@Mapper
public class NewsLetterJpaMapper {

    public NewsLetterJpaEntity toJpaEntity(NewsLetter newsLetter) {
        if (newsLetter == null) {
            return null;
        }

        return NewsLetterJpaEntity.builder()
            .id(newsLetter.getNewsLetterId() != null ? newsLetter.getNewsLetterId().getValue()
	: null)
            .editorId(newsLetter.getEditorId() != null ? newsLetter.getEditorId().getValue() : null)
            // todo: 썸네일이 Null 이라면 대체 이미지를 집어넣어야함
            .thumbnail(
	newsLetter.getThumbnail() != null ? newsLetter.getThumbnail().getUrl() : null)
            .title(newsLetter.getTitle().getTitle())
            .content(newsLetter.getContent().getContent())
            .keywords(newsLetter.getKeywords().toSentence())
            .category(newsLetter.getCategory())
            .type(newsLetter.getType())
            .summary(newsLetter.getSummary().toSentence())
            .viewCount(newsLetter.getViewCount().getCount())
            .scrapCount(newsLetter.getScrapCount().getCount())
            .build();
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
            .viewCount(newsLetter.getViewCount().getCount())
            .scrapCount(newsLetter.getScrapCount().getCount())
            .build();
    }

    public NewsLetter toDomain(NewsLetterJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        return NewsLetter.builder()
            .newsLetterId(Id.of(jpaEntity.getId()))
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
            .build();
    }

    public List<NewsLetter> toDomainList(List<NewsLetterJpaEntity> newsLetters) {
        return newsLetters.stream()
            .map(this::toDomain)
            .toList();
    }
}
