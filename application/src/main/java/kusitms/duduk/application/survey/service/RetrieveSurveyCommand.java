package kusitms.duduk.application.survey.service;

import kusitms.duduk.common.exception.ErrorMessage;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.survey.dto.SurveyDtoMapper;
import kusitms.duduk.core.survey.dto.response.TodaySurveyResponse;
import kusitms.duduk.core.survey.port.input.RetrieveSurveyUseCase;
import kusitms.duduk.core.survey.port.output.LoadSurveyPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.survey.Survey;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RetrieveSurveyCommand implements RetrieveSurveyUseCase {

    private final LoadSurveyPort loadSurveyPort;
    private final LoadUserPort loadUserPort;
    private final SurveyDtoMapper surveyDtoMapper;

    @Override
    public TodaySurveyResponse retrieveTodaySurvey(String email) {
        Survey survey = loadSurveyPort.findTodaySurvey()
            .orElseThrow(() -> new NotExistsException(ErrorMessage.SURVEY_NOT_FOUND.getMessage()));

        User user = getUser(email);

        boolean isVoted = survey.isVoted(user.getId());

        return surveyDtoMapper.toTodaySurveyResponse(survey, user.getNickname().getValue(),
            isVoted);
    }

    @Override
    public Survey retrieveSurveyDetails(Long surveyId) {
        return loadSurveyPort.findById(surveyId)
                    .orElseThrow(() -> new NotExistsException(ErrorMessage.SURVEY_NOT_FOUND.getMessage()));
    }

    private User getUser(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(ErrorMessage.USER_NOT_FOUND.getMessage()));
    }
}
