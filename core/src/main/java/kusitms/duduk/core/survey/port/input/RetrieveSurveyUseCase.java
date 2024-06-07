package kusitms.duduk.core.survey.port.input;

import kusitms.duduk.core.survey.dto.response.TodaySurveyResponse;
import kusitms.duduk.domain.survey.Survey;

public interface RetrieveSurveyUseCase {

    TodaySurveyResponse retrieveTodaySurvey(String email);

    Survey retrieveSurveyDetails(Long surveyId);
}
