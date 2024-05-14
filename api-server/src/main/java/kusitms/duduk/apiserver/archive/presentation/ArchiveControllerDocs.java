package kusitms.duduk.apiserver.archive.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.common.exception.ErrorResponse;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedNewsLetterResponse;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedTermResponse;
import kusitms.duduk.core.newsletter.dto.response.ArchiveNewsLetterResponse;
import kusitms.duduk.core.term.dto.response.ArchiveTermResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Archive", description = "아카이브 API")
public interface ArchiveControllerDocs {

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "아카이브 뉴스레터 조회", description = "아카이브에 저장되어 있는 뉴스레터를 조회합니다.")
    ResponseEntity<RetrieveArchivedNewsLetterResponse> retrieveArchivedNewsLetters(
        @AuthenticationPrincipal final CustomUserDetails customUserDetails,
        @PathVariable final String category
    );

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "아카이브 단어 조회", description = "아카이브에 저장되어 있는 단어를 조회합니다.")
    ResponseEntity<RetrieveArchivedTermResponse> retrieveArchivedTerms(
        @AuthenticationPrincipal final CustomUserDetails customUserDetails);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "뉴스레터 아카이브", description = "뉴스레터를 아카이브에 저장합니다.")
    ResponseEntity<ArchiveNewsLetterResponse> archiveNewsLetter(
        @AuthenticationPrincipal final CustomUserDetails customUserDetails,
        @PathVariable final Long newsLetterId
    );

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "단어 아카이브", description = "단어를 아카이브에 저장합니다.")
    ResponseEntity<ArchiveTermResponse> archiveTerm(
        @AuthenticationPrincipal final CustomUserDetails customUserDetails,
        @PathVariable final Long termId
    );
}
