package kusitms.duduk.core.thinking.dto;

import java.util.ArrayList;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingDetailResponse;
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

    public RetrieveThinkingDetailResponse toDto(Thinking thinking) {
        return RetrieveThinkingDetailResponse.builder()
            .thinkingId(thinking.getId().getValue())
            .userId(thinking.getUserId().getValue())
            .newsLetterId(thinking.getNewsLetterId().getValue())
            .thumbnailUrl(thinking.getThumbnail().getUrl())
            .comment(thinking.getComment().getValue())
            .summarizedComment(thinking.getSummarizedComment().getValue())
            .isCloudExist(thinking.isCloudExist())
            .thinkingCloud(thinking.getThinkingCloud().stream()
	.map(Sentence::getValue)
	.toList())
            .build();
    }
}
