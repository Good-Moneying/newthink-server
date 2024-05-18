package kusitms.duduk.apiserver.newsletter.presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterTestResponse;
import kusitms.duduk.core.newsletter.port.input.RetrieveNewsLetterQuery;
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
@RequestMapping("/api/newsletters")
public class NewsLetterController implements NewsLetterControllerDocs {

    private final RetrieveNewsLetterQuery retrieveNewsLetterQuery;

    @GetMapping("/{newsLetterId}")
    public ResponseEntity<NewsLetterDetailResponse> retrieveNewsLetterDetail(
        @PathVariable(name = "newsLetterId") Long newsLetterId,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(retrieveNewsLetterQuery.retrieveNewsLetterDetail(
            userDetails.getEmail(), newsLetterId), HttpStatus.OK);
    }

    @GetMapping("/test")
    public NewsLetterTestResponse test() {
        return NewsLetterTestResponse.builder()
            .publishedAt(LocalDate.now())
            .editor("Amy")
            .blocks(new ArrayList<>(Arrays.asList(
	new NewsLetterTestResponse.Block("paragraph", "불과 몇 주 사이에 미국 통화 정책을 ..."),
	new NewsLetterTestResponse.Block("paragraph", "지난 16일(현지시간) 제롬 파월 미국 ..."),
	new NewsLetterTestResponse.Block("photo",
	    "https://image.shutterstock.com/image-photo/")
            )))
            .comments(new ArrayList<>(Arrays.asList(
	new NewsLetterTestResponse.Comment("Eric", "좋은 기사네요!"),
	new NewsLetterTestResponse.Comment("Bob", "좋은 기사네요!"),
	new NewsLetterTestResponse.Comment("Charlie", "좋은 기사네요!")
            )))
            .isCommented(true)
            .build();
    }
}
