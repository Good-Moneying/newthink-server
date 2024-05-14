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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/archives")
public class ArchiveController implements ArchiveControllerDocs {

    private final RetrieveArchiveUseCase retrieveArchiveUseCase;
    private final ArchiveNewsLetterUseCase archiveNewsLetterUseCase;
    private final ArchiveTermUseCase archiveTermUseCase;

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

    @GetMapping("/{newsLetterId}")
    public ResponseEntity<ArchiveNewsLetterResponse> archiveNewsLetter(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable(name = "newsLetterId") Long newsLetterId) {
        return new ResponseEntity<>(
            archiveNewsLetterUseCase.archive(customUserDetails.getEmail(), newsLetterId),
            HttpStatus.OK);
    }

    @GetMapping("/{termId}")
    public ResponseEntity<ArchiveTermResponse> archiveTerm(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable(name = "termId") Long termId) {
        return new ResponseEntity<>(
            archiveTermUseCase.archive(customUserDetails.getEmail(), termId), HttpStatus.OK);
    }

    @GetMapping("/newsletters/{category}/test")
    public ResponseEntity<RetrieveArchivedNewsLetterResponse> retrieveArchivedNewsLettersTest(
        @AuthenticationPrincipal final CustomUserDetails customUserDetails,
        @PathVariable(name = "category") String category) {

        return new ResponseEntity<>(
            RetrieveArchivedNewsLetterResponse.builder()
	.newsLetters(
	    List.of(
	        ArchivedNewsLetter.of(1L, "최근의 글로벌 경제 동향에 대한 전문가 분석", "Finance",
	            LocalDateTime.now()),
	        ArchivedNewsLetter.of(2L, "2024년 주목해야 할 신기술에 대한 리뷰", "Policy",
	            LocalDateTime.now()),
	        ArchivedNewsLetter.of(3L, "매일의 건강을 유지하기 위한 실용적인 팁", "Securities",
	            LocalDateTime.now())
	    )
	)
	.build()
            , HttpStatus.OK);
    }

    @GetMapping("/terms/test")
    public ResponseEntity<RetrieveArchivedTermResponse> retrieveArchivedTermsTest(
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(
            RetrieveArchivedTermResponse.builder()
	.terms(
	    List.of(
	        ArchivedTerm.of(1L, "양자 컴퓨팅", "Quantum Computing"),
	        ArchivedTerm.of(2L, "인공지능", "Artificial Intelligence"),
	        ArchivedTerm.of(3L, "블록체인", "Blockchain"
	        )
	    )
	).build(),
            HttpStatus.OK);
    }
}
