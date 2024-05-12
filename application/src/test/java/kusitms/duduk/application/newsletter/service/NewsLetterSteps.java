package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.newsletter.vo.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import kusitms.duduk.domain.newsletter.vo.Type;

public class NewsLetterSteps {

    public static CreateNewsLetterRequest AI_뉴스_레터_생성_요청() {
        String thumbnail = "https://image.com/tesla.jpg";
        String title = "테슬라 주가 갑자기 오르는 이유는?";
        String content = "불과 몇 주 사이에 미국 토화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까진 다들 '오해 6월에 금리 인하가 시작될 것'이라고 했다";
        String keywords = "테슬라, 전기차, 미국 연준";
        String category = "FINANCE";
        String summary = "물가 상승률이 예상보다 높다.\n급격한 기준 금리 인상한다\n이민자 증가한다\n";
        final String type = "AI";

        CreateNewsLetterRequest request = new CreateNewsLetterRequest(
            thumbnail,
            title,
            content,
            keywords,
            category,
            summary,
            type
        );
        return request;
    }

    public static CreateNewsLetterRequest EDITOR_뉴스_레터_생성_요청() {
        String thumbnail = "https://image.com/tesla.jpg";
        String title = "테슬라 주가 갑자기 오르는 이유는?";
        String content = "불과 몇 주 사이에 미국 토화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까진 다들 '오해 6월에 금리 인하가 시작될 것'이라고 했다";
        String keywords = "테슬라, 전기차, 미국 연준";
        String category = "FINANCE";
        String summary = "물가 상승률이 예상보다 높다.\n급격한 기준 금리 인상한다\n이민자 증가한다\n";
        final String type = "EDITOR";

        CreateNewsLetterRequest request = new CreateNewsLetterRequest(
            thumbnail,
            title,
            content,
            keywords,
            category,
            summary,
            type
        );
        return request;
    }

    public static NewsLetter AI_뉴스_레터_생성() {
        CreateNewsLetterRequest request = AI_뉴스_레터_생성_요청();
        NewsLetter newsLetter = NewsLetter.builder()
            .thumbnail(Thumbnail.from(request.thumbnail()))
            .title(Title.from(request.title()))
            .content(Content.from(request.content()))
            .keywords(Keywords.from(request.keywords()))
            .category(Category.FINANCE)
            .summary(Summary.from(request.summary()))
            .type(Type.AI)
            .viewCount(Count.initial())
            .scrapCount(Count.initial())
            .build();

        return newsLetter;
    }
}
