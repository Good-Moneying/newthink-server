package kusitms.duduk.core.survey.port.output;

import kusitms.duduk.domain.survey.Survey;

public interface SaveSurveyPort {

    Survey save(Survey survey);
}
