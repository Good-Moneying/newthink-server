package kusitms.duduk.core.thinking.dto;

import java.util.ArrayList;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.global.Thumbnail;
import kusitms.duduk.domain.thinking.Thinking;

@Mapper
public class ThinkingDtoMapper {

    public Thinking create(CreateThinkingRequest request) {
        return Thinking.builder()
            .userId(Id.of(request.userId()))
            .newsLetterId(Id.of(request.newsLetterId()))
            .thumbnail(Thumbnail.from(request.thumbnail()))
            .comment(Sentence.from(request.comment()))
            .summarizedComment(Sentence.from(request.summarizedComment()))
            .isCloudExist(false)
            .thinkingCloud(new ArrayList<>())
            .build();
    }
}
