package kusitms.duduk.core.newsletter.dto;

import kusitms.duduk.core.annotation.Mapper;
import kusitms.duduk.core.newsletter.dto.request.CreateAiNewsLetterRequest;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.newsletter.vo.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import kusitms.duduk.domain.newsletter.vo.Type;

@Mapper
public class NewsLetterDtoMapper {

    public NewsLetter toDomain(CreateAiNewsLetterRequest request) {
        return NewsLetter.builder()
            .thumbnail(Thumbnail.from(request.thumbnail()))
            .title(Title.from(request.title()))
            .content(Content.from(request.content()))
            .keywords(Keywords.from(request.keywords()))
            .category(Category.from(request.category()))
            .summary(Summary.from(request.summary()))
            .type(Type.valueOf(request.aiType()))
            .build();
    }
}