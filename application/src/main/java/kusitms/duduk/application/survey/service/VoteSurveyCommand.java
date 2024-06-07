package kusitms.duduk.application.survey.service;

import kusitms.duduk.common.exception.ErrorMessage;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.survey.port.input.VoteSurveyUseCase;
import kusitms.duduk.core.survey.port.output.LoadSurveyPort;
import kusitms.duduk.core.survey.port.output.UpdateSurveyPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.survey.Survey;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VoteSurveyCommand implements VoteSurveyUseCase {

    private final LoadUserPort loadUserPort;
    private final LoadSurveyPort loadSurveyPort;
    private final UpdateSurveyPort updateSurveyPort;

    @Transactional
    public void agreeSurvey(Long surveyId, String email) {
        User user = getUser(email);
        Survey survey = getSurvey(surveyId);

        survey.agree(user.getId());
        updateSurveyPort.updateSurvey(survey);
    }

    @Transactional
    public void disagreeSurvey(Long surveyId, String email) {
        User user = getUser(email);
        Survey survey = getSurvey(surveyId);

        survey.disagree(user.getId());
        updateSurveyPort.updateSurvey(survey);
    }

    private User getUser(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(ErrorMessage.USER_NOT_FOUND.getMessage()));
    }

    private Survey getSurvey(Long surveyId) {
        return loadSurveyPort.findById(surveyId)
            .orElseThrow(() -> new NotExistsException(ErrorMessage.SURVEY_NOT_FOUND.getMessage()));
    }
}
