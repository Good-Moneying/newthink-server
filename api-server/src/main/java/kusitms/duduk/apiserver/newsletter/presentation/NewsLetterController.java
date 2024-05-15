package kusitms.duduk.apiserver.newsletter.presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterTestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/newsletters")
public class NewsLetterController implements NewsLetterControllerDocs{

    @GetMapping("/{newsLetterId}")
    public NewsLetterDetailResponse retrieveNewsLetterDetail(
        @PathVariable(name = "newsLetterId") Long newsLetterId) {
        return retrieveNewsLetterDetail(newsLetterId);
    }

    @GetMapping("/test")
    public NewsLetterTestResponse test() {
        return NewsLetterTestResponse.builder()
            .publishedAt(LocalDate.now())
            .editor("Amy")
            .blocks(new ArrayList<>(Arrays.asList(
	new NewsLetterTestResponse.Block("headline", "테슬라 주가 갑자기 오른 이유는?"),
	new NewsLetterTestResponse.Block("summary", "물가상승률\n급격한 기준 금리 인상\n이민자 증가"),
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
