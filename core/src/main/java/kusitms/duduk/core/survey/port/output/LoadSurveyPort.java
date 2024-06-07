package kusitms.duduk.core.survey.port.output;

import java.util.Optional;
import kusitms.duduk.domain.survey.Survey;

public interface LoadSurveyPort {

    Optional<Survey> findTodaySurvey();

    Optional<Survey> findById(Long surveyId);
}
