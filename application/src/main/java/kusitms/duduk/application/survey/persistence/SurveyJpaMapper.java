package kusitms.duduk.application.survey.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.application.comment.persistence.CommentJpaMapper;
import kusitms.duduk.application.survey.persistence.entity.SurveyJpaEntity;
import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.survey.Survey;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Mapper
public class SurveyJpaMapper {

    private CommentJpaMapper commentJpaMapper;

    public Survey toDomain(SurveyJpaEntity surveyJpaEntity) {
        return Survey.builder()
            .id(Id.of(surveyJpaEntity.getId()))
            .creatorId(Id.of(surveyJpaEntity.getCreatorId()))
            .description(surveyJpaEntity.getDescription())
            .participants(Count.from(
	surveyJpaEntity.getAgreedUserIds().size() + surveyJpaEntity.getDisagreedUserIds()
	    .size()))
            .agreedUserIds(surveyJpaEntity.getAgreedUserIds())
            .disagreedUserIds(surveyJpaEntity.getDisagreedUserIds())
            .comments(surveyJpaEntity.getComments().stream()
	.map(commentJpaEntity -> commentJpaMapper.toDomain(commentJpaEntity))
	.collect(Collectors.toList())
            )
            .build();
    }

    public SurveyJpaEntity toJpaEntity(Survey survey) {
        return SurveyJpaEntity.builder()
            .id(survey.getId() != null ? survey.getId().getValue() : null)
            .creatorId(survey.getCreatorId().getValue())
            .description(survey.getDescription())
            .agreedUserIds(survey.getAgreedUserIds())
            .disagreedUserIds(survey.getDisagreedUserIds())
            .comments(survey.getComments() != null ? survey.getComments().stream()
	.map(comment -> commentJpaMapper.toJpaEntity(comment))
	.collect(Collectors.toList())
	: new ArrayList<>()
            )
            .build();
    }

    public SurveyJpaEntity toJpaEntity(Survey survey, SurveyJpaEntity persistedSurvey) {
        return persistedSurvey.builder()
            .description(survey.getDescription())
            .agreedUserIds(survey.getAgreedUserIds())
            .disagreedUserIds(survey.getDisagreedUserIds())
            .comments(survey.getComments().stream()
	.map(comment -> commentJpaMapper.toJpaEntity(comment))
	.collect(Collectors.toList())
            )
            .build();
    }
}
