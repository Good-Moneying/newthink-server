package kusitms.duduk.apiserver.survey.presentation;

import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.survey.dto.response.TodaySurveyResponse;
import kusitms.duduk.core.survey.port.input.RetrieveSurveyUseCase;
import kusitms.duduk.core.survey.port.input.VoteSurveyUseCase;
import kusitms.duduk.domain.survey.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final RetrieveSurveyUseCase retrieveSurveyUseCase;
    private final VoteSurveyUseCase voteSurveyUseCase;

    @GetMapping
    public ResponseEntity<TodaySurveyResponse> retrieveTodaySurvey(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return new ResponseEntity<>(
            retrieveSurveyUseCase.retrieveTodaySurvey(userDetails.getEmail()),
            HttpStatus.OK);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<Survey> retrieveSurveyDetails(
        @PathVariable(name = "surveyId") Long surveyId
    ) {
        return new ResponseEntity<>(
            retrieveSurveyUseCase.retrieveSurveyDetails(surveyId),
            HttpStatus.OK);
    }


    @PostMapping("/agree")
    public ResponseEntity<Void> agreeSurvey(
        @RequestParam Long surveyId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        voteSurveyUseCase.agreeSurvey(surveyId, userDetails.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> disagreeSurvey(
        @RequestParam Long surveyId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        voteSurveyUseCase.disagreeSurvey(surveyId, userDetails.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
