package kusitms.duduk.application.survey.service;

import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.core.survey.port.output.DeleteSurveyPort;
import kusitms.duduk.core.survey.port.output.SaveSurveyPort;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.survey.Survey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class RetrieveSurveyCommandTest {

    @Autowired
    private SaveSurveyPort saveSurveyPort;

    @Autowired
    private DeleteSurveyPort deleteSurveyPort;

    @AfterEach
    void cleanUp() {
        deleteSurveyPort.deleteAll();
    }

    @Test
    void 설문을_생성한다() {
        // given
        Survey savedSurvey = 설문_저장();

        // then
        assertThat(savedSurvey.getDescription()).isEqualTo("설문 주제");
        assertThat(savedSurvey.getCreatorId().getValue()).isEqualTo(1L);
        assertThat(savedSurvey.getAgreedUserIds()).isEmpty();
        assertThat(savedSurvey.getDisagreedUserIds()).isEmpty();
    }

    private Survey 설문_저장() {
        Survey survey = Survey.builder()
            .creatorId(Id.of(1L))
            .description("설문 주제")
            .build();

        // when
        Survey savedSurvey = saveSurveyPort.save(survey);
        return savedSurvey;
    }
}