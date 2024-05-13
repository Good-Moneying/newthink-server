package kusitms.duduk.apiserver.archive.presentation;

import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.archive.dto.response.ArchiveResponse;
import kusitms.duduk.core.archive.port.input.RetrieveArchiveUseCase;
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
@RequestMapping("/api/archies")
public class ArchiveController implements ArchiveControllerDocs {

    private final RetrieveArchiveUseCase retrieveArchiveUseCase;

    @GetMapping("/newsletters/{category}")
    public ResponseEntity<ArchiveResponse> retrieveArchivedNewsLetters(
        @AuthenticationPrincipal final CustomUserDetails customUserDetails,
        @PathVariable(name = "category") String category) {

        return new ResponseEntity<>(
            retrieveArchiveUseCase.retrieveNewsLetters(customUserDetails.getEmail(),
	Category.from(category)), HttpStatus.OK);
    }

    @GetMapping("/terms")
    public ResponseEntity<ArchiveResponse> retrieveArchivedTerms(
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(
            retrieveArchiveUseCase.retrieveTerms(customUserDetails.getEmail()),
            HttpStatus.OK);
    }
}
