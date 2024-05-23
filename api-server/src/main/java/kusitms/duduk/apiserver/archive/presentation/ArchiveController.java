package kusitms.duduk.apiserver.archive.presentation;

import java.time.LocalDateTime;
import java.util.List;
import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedNewsLetterResponse;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedNewsLetterResponse.ArchivedNewsLetter;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedTermResponse;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedTermResponse.ArchivedTerm;
import kusitms.duduk.core.archive.port.input.RetrieveArchiveUseCase;
import kusitms.duduk.core.newsletter.dto.response.ArchiveNewsLetterResponse;
import kusitms.duduk.core.newsletter.port.input.ArchiveNewsLetterUseCase;
import kusitms.duduk.core.term.dto.response.ArchiveTermResponse;
import kusitms.duduk.core.term.port.input.ArchiveTermUseCase;
import kusitms.duduk.domain.global.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/archives")
public class ArchiveController implements ArchiveControllerDocs {

    private final RetrieveArchiveUseCase retrieveArchiveUseCase;
    private final ArchiveNewsLetterUseCase archiveNewsLetterUseCase;
    private final ArchiveTermUseCase archiveTermUseCase;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.info("ArchiveController#test() start");
        return new ResponseEntity<>("Test endpoint is working", HttpStatus.OK);
    }

    @GetMapping("/newsletters/{category}")
    public ResponseEntity<RetrieveArchivedNewsLetterResponse> retrieveArchivedNewsLetters(
        @AuthenticationPrincipal final CustomUserDetails customUserDetails,
        @PathVariable(name = "category") String category) {

        return new ResponseEntity<>(
            retrieveArchiveUseCase.retrieveNewsLetters(customUserDetails.getEmail(),
	Category.from(category)), HttpStatus.OK);
    }

    @GetMapping("/terms")
    public ResponseEntity<RetrieveArchivedTermResponse> retrieveArchivedTerms(
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(
            retrieveArchiveUseCase.retrieveTerms(customUserDetails.getEmail()),
            HttpStatus.OK);
    }

    @PostMapping("/newsletters/{newsLetterId}")
    public ResponseEntity<ArchiveNewsLetterResponse> archiveNewsLetter(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable(name = "newsLetterId") Long newsLetterId) {
        return new ResponseEntity<>(
            archiveNewsLetterUseCase.archive(customUserDetails.getEmail(), newsLetterId),
            HttpStatus.OK);
    }

    @PostMapping("/newsletters/{newsLetterId}/{category}")
    public ResponseEntity<ArchiveNewsLetterResponse> archiveNewsLetterWithCategory(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable(name = "newsLetterId") Long newsLetterId,
        @PathVariable(name = "category") String category) {
        return new ResponseEntity<>(
            archiveNewsLetterUseCase.archiveWithCategory(customUserDetails.getEmail(), newsLetterId,
	category),
            HttpStatus.OK);
    }

    @PostMapping("/terms/{termId}")
    public ResponseEntity<ArchiveTermResponse> archiveTerm(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable(name = "termId") Long termId) {
        log.info("ArchiveController#archiveTerm() start\n");
        return new ResponseEntity<>(
            archiveTermUseCase.archive(customUserDetails.getEmail(), termId), HttpStatus.OK);
    }
}
