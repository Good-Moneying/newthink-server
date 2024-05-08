package kusitms.duduk.core.newsletter.dto;

import kusitms.duduk.core.annotation.Mapper;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.newsletter.vo.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import kusitms.duduk.domain.newsletter.vo.Type;

@Mapper
public class NewsLetterDtoMapper {

    public NewsLetter toDomain(CreateNewsLetterRequest request) {
        return NewsLetter.builder()
            .thumbnail(Thumbnail.from(request.thumbnail()))
            .title(Title.from(request.title()))
            .content(Content.from(request.content()))
            .keywords(Keywords.from(request.keywords()))
            .category(Category.from(request.category()))
            .summary(Summary.from(request.summary()))
            .type(Type.valueOf(request.aiType()))
            .viewCount(Count.initial())
            .scrapCount(Count.initial())
            .build();
    }

    public NewsLetter toDomain(CreateNewsLetterRequest request, Id editorId) {
        return NewsLetter.builder()
            .editorId(editorId)
            .thumbnail(Thumbnail.from(request.thumbnail()))
            .title(Title.from(request.title()))
            .content(Content.from(request.content()))
            .keywords(Keywords.from(request.keywords()))
            .category(Category.from(request.category()))
            .summary(Summary.from(request.summary()))
            .type(Type.valueOf(request.aiType()))
            .viewCount(Count.initial())
            .scrapCount(Count.initial())
            .build();
    }

    public NewsLetterResponse toDto(NewsLetter newsLetter) {
        return NewsLetterResponse.builder()
            .title(newsLetter.getTitle().getTitle())
            .content(newsLetter.getContent().getContent())
            .keywords(newsLetter.getKeywords().toSentence())
            .category(newsLetter.getCategory().name())
            .summary(newsLetter.getSummary().toSentence())
            .type(newsLetter.getType().name())
            .build();
    }
}