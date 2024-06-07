package kusitms.duduk.core.survey.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder(toBuilder = true)
public record TodaySurveyResponse(Long surveyId, LocalDateTime createdAt, String description, int participants,
		  String creatorName, boolean isVoted, int proportionOfAgree,
		  int proportionOfDisagree) {

}
