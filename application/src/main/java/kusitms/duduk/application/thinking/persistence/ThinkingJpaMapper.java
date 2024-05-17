package kusitms.duduk.application.thinking.persistence;

import kusitms.duduk.application.thinking.persistence.entity.ThinkingJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.thinking.Thinking;
import java.util.ArrayList;
import java.util.stream.Collectors;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.global.Thumbnail;

@Mapper
public class ThinkingJpaMapper {

    public Thinking toDomain(ThinkingJpaEntity thinkingJpaEntity) {
        if (thinkingJpaEntity == null) {
            return null;
        }

        return Thinking.builder()
            .id(Id.of(thinkingJpaEntity.getId()))
            .userId(Id.of(thinkingJpaEntity.getUserId()))
            .newsLetterId(Id.of(thinkingJpaEntity.getNewsLetterId()))
            .thumbnail(Thumbnail.from(thinkingJpaEntity.getThumbnail()))
            .comment(Sentence.from(thinkingJpaEntity.getComment()))
            .summarizedComment(Sentence.from(thinkingJpaEntity.getSummarizedComment()))
            .isCloudExist(thinkingJpaEntity.isCloudExist())
            .thinkingCloud(thinkingJpaEntity.getThinkingCloud().stream()
	.map(Sentence::from)
	.collect(Collectors.toCollection(ArrayList::new)))
            .build();
    }

    public ThinkingJpaEntity toJpaEntity(Thinking thinking) {
        if (thinking == null) {
            return null;
        }

        return ThinkingJpaEntity.builder()
            .id(thinking.getId() != null ? thinking.getId().getValue() : null)
            .userId(thinking.getUserId() != null ? thinking.getUserId().getValue() : null)
            .newsLetterId(
	thinking.getNewsLetterId() != null ? thinking.getNewsLetterId().getValue() : null)
            .thumbnail(thinking.getThumbnail() != null ? thinking.getThumbnail().getUrl() : null)
            .comment(thinking.getComment() != null ? thinking.getComment().getValue() : null)
            .summarizedComment(
	thinking.getSummarizedComment() != null ? thinking.getSummarizedComment().getValue()
	    : null)
            .isCloudExist(thinking.isCloudExist())
            .thinkingCloud(thinking.getThinkingCloud().stream()
	.map(Sentence::getValue)
	.collect(Collectors.toCollection(ArrayList::new)))
            .build();
    }
}
