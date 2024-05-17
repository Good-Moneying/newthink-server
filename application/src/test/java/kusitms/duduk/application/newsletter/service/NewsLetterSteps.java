package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
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
        String category = "POLICY";
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

    public static NewsLetter EDITOR_뉴스_레터_생성(Long editorId) {
        String thumbnail = "https://image.com/tesla.jpg";
        String title = "테슬라 주가 갑자기 오르는 이유는?";
        String content =
            "                \"불과 몇 주 사이에 미국 통화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까지만 해도 ‘올해 6월에 금리 인하가 시작될 것’이라 했는데, 이젠 “아직 멀었다”는 사람이 많아졌죠.\\n\\n\" +\n"
	+ "                \"지난 16일(현지시간) 제롬 파월 미국 연방준비제도(Fed-연준) 의장은 사실상 6월 금리 인하가 무산됐음을 인정했어요. 파월 의장은 “최근 데이터는 (금리 인하에 대한) 확신을 주지 못했고, 그런 확신을 얻는 데에는 예상보다 더 오랜 시간이 걸릴 것”이라고 말했어요.\\n\\n\" +\n"
	+ "                \"이제 미국의 기준금리 인하는 9월 이후에 가능하다는 전망이 우세하고, 인하 시점을 내년으로 보는 사람들 꽤 많아졌어요. 오히려 기준금리를 지금보다 조금 더 올릴 수 있다는 전망까지 나오고 있어요.\\n\\n\" +\n"
	+ "                \"왜 갑자기 분위기가 바뀐 걸까요? 경제 전문가들이 대부분 이런 변화를 예상하지 못했던 이유는 뭘까요?\\n\\n\" +\n"
	+ "                \"하지만 비슷하게 긴축에 나섰던 다른 주요국들이 경제 침체를 겪는 동안, 미국은 상대적으로 경제 호황을 누리고 있어요. 빠른 기준금리 인상에 따라 경착륙(심한 경기 침체), 연착륙(약한 경기 침체), 스태그플레이션 등 여러 침체 시나리오를 제시했던 전문가들의 예상이 모두 빗나간 거예요.\";\n";
        String keywords = "테슬라, 전기차, 미국 연준";
        String summary = "물가 상승률이 예상보다 높다.\n급격한 기준 금리 인상한다\n이민자 증가한다\n";

        return NewsLetter.builder()
            .editorId(Id.of(editorId))
            .thumbnail(Thumbnail.from(thumbnail))
            .title(Title.from(title))
            .content(Content.from(content))
            .keywords(Keywords.from(keywords))
            .category(Category.POLICY)
            .summary(Summary.from(summary))
            .type(Type.EDITOR)
            .viewCount(Count.initial())
            .scrapCount(Count.initial())
            .build();
    }

    public static NewsLetter AI_FINANCE_뉴스_레터_생성() {
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

    public static NewsLetter AI_뉴스_레터_생성() {
        String thumbnail = "https://image.com/tesla.jpg";
        String title = "테슬라 주가 갑자기 오르는 이유는?";
        String keywords = "테슬라, 전기차, 미국 연준";
        String summary = "물가 상승률이 예상보다 높다.\n급격한 기준 금리 인상한다\n이민자 증가한다\n";

        return NewsLetter.builder()
            .thumbnail(Thumbnail.from(thumbnail))
            .title(Title.from(title))
            .keywords(Keywords.from(keywords))
            .category(Category.POLICY)
            .summary(Summary.from(summary))
            .type(Type.AI)
            .viewCount(Count.initial())
            .scrapCount(Count.initial())
            .build();
    }
}
