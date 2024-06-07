package kusitms.duduk.application.survey.service;

import static org.assertj.core.api.Assertions.assertThat;

import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.survey.port.input.RetrieveSurveyUseCase;
import kusitms.duduk.core.survey.port.input.VoteSurveyUseCase;
import kusitms.duduk.core.survey.port.output.DeleteSurveyPort;
import kusitms.duduk.core.survey.port.output.SaveSurveyPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.survey.Survey;
import kusitms.duduk.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class VoteSurveyCommandTest {

    @Autowired
    private VoteSurveyUseCase voteSurveyUseCase;

    @Autowired
    private RetrieveSurveyUseCase retrieveSurveyUseCase;

    @Autowired
    private SaveSurveyPort saveSurveyPort;

    @Autowired
    private DeleteSurveyPort deleteSurveyPort;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    private User savedUser;

    @BeforeEach
    void beforeEach() {
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);
    }

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
        deleteSurveyPort.deleteAll();
    }

    @Test
    void 설문을_생성한다() {
        // given
        Survey savedSurvey = 설문_저장();

        // then
        voteSurveyUseCase.agreeSurvey(savedSurvey.getId().getValue(),
            savedUser.getEmail().getValue());

        // when
        Survey survey = retrieveSurveyUseCase.retrieveSurveyDetails(savedSurvey.getId().getValue());
        assertThat(survey.getAgreedUserIds()).contains(savedUser.getId().getValue());
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
