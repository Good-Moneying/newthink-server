package kusitms.duduk.core.survey.port.input;

public interface VoteSurveyUseCase {

    void agreeSurvey(Long surveyId, String email);

    void disagreeSurvey(Long surveyId, String email);
}
