package kusitms.duduk.core.newsletter.dto;

import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
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
            .type(Type.from(request.type()))
            .viewCount(Count.initial())
            .scrapCount(Count.initial())
            .build();
    }

    // 에디터가 뉴스레터 작성 시 에디터의 아이디를 매핑하기 위한 메서드
    public NewsLetter toDomain(CreateNewsLetterRequest request, Id editorId) {
        return NewsLetter.builder()
            .editorId(editorId)
            .thumbnail(Thumbnail.from(request.thumbnail()))
            .title(Title.from(request.title()))
            .content(Content.from(request.content()))
            .keywords(Keywords.from(request.keywords()))
            .category(Category.from(request.category()))
            .summary(Summary.from(request.summary()))
            .type(Type.from(request.type()))
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

    public NewsLetterThumbnailResponse toThumbnailDto(NewsLetter newsLetter, boolean isScrapped) {
        return NewsLetterThumbnailResponse.builder()
            .id(newsLetter.getNewsLetterId().getValue())
            .thumbnail(newsLetter.getThumbnail().getUrl())
            .title(newsLetter.getTitle().getTitle())
            .createdAt(newsLetter.getCreatedAt())
            .keywords(newsLetter.getKeywords().toSentence())
            .category(newsLetter.getCategory().name())
            .type(newsLetter.getType().name())
            .isScrapped(isScrapped)
            .build();
    }
}