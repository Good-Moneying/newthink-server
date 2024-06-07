package kusitms.duduk.application.survey.persistence;

import java.util.Optional;
import kusitms.duduk.application.survey.persistence.entity.SurveyJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.survey.port.output.DeleteSurveyPort;
import kusitms.duduk.core.survey.port.output.LoadSurveyPort;
import kusitms.duduk.core.survey.port.output.SaveSurveyPort;
import kusitms.duduk.core.survey.port.output.UpdateSurveyPort;
import kusitms.duduk.domain.survey.Survey;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class SurveyPersistenceAdapter implements LoadSurveyPort, UpdateSurveyPort, SaveSurveyPort,
    DeleteSurveyPort {

    private final SurveyRepository surveyRepository;
    private final SurveyJpaMapper surveyJpaMapper;

    @Override
    public Survey save(final Survey survey) {
        SurveyJpaEntity surveyJpaEntity = surveyJpaMapper.toJpaEntity(survey);
        SurveyJpaEntity savedSurvey = surveyRepository.save(surveyJpaEntity);
        return surveyJpaMapper.toDomain(savedSurvey);
    }

    @Override
    public Optional<Survey> findTodaySurvey() {
        return surveyRepository.findTodaySurvey()
            .map(surveyJpaMapper::toDomain);
    }

    @Override
    public Optional<Survey> findById(final Long surveyId) {
        return surveyRepository.findById(surveyId)
            .map(surveyJpaMapper::toDomain);
    }

    @Override
    public void updateSurvey(final Survey survey) {
        Long surveyId = survey.getId().getValue();

        surveyRepository.findById(surveyId)
            .map(persistedSurvey ->
	surveyJpaMapper.toJpaEntity(survey, persistedSurvey)
            )
            .map(surveyRepository::save);
    }

    @Override
    public void deleteAll() {
        surveyRepository.deleteAll();
    }
}
