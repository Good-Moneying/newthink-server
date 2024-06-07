package kusitms.duduk.core.survey.dto;

import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.survey.dto.response.TodaySurveyResponse;
import kusitms.duduk.domain.survey.Survey;

@Mapper
public class SurveyDtoMapper {

    public TodaySurveyResponse toTodaySurveyResponse(Survey survey, String creatorName,
        boolean isVoted) {
        return TodaySurveyResponse.builder()
            .surveyId(survey.getId().getValue())
            .createdAt(survey.getCreatedAt())
            .description(survey.getDescription())
            .participants(survey.getParticipants().getCount())
            .creatorName(creatorName)
            .isVoted(isVoted)
            .proportionOfAgree(
	calculateProportion(survey.getParticipants().getCount(),
	    survey.getAgreedUserIds().size())
            )
            .proportionOfDisagree(
	calculateProportion(survey.getParticipants().getCount(),
	    survey.getDisagreedUserIds().size())
            )
            .build();
    }

    private int calculateProportion(int total, int part) {
        return (int) ((double) part / total * 100);
    }
}
