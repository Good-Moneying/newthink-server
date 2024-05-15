package kusitms.duduk.apiserver.newsletter.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.duduk.common.exception.ErrorResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "NewsLetter", description = "뉴스레터 API")
public interface NewsLetterControllerDocs {

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "뉴스레터 상세 조회", description = "뉴스레터를 상세 조회 합니다")
    public NewsLetterDetailResponse retrieveNewsLetterDetail(
        @PathVariable(name = "newsLetterId") Long newsLetterId);
}
