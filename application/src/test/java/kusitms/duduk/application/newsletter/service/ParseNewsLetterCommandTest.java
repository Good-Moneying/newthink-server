package kusitms.duduk.application.newsletter.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse.Block;
import kusitms.duduk.core.newsletter.port.input.ParseNewsLetterUseCase;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.Content;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ParseNewsLetterCommandTest {

    @Autowired
    private ParseNewsLetterUseCase parseNewsLetterUseCase;
    @Test
    void parseContentToBlocks() {
        // given
        String content = "불과 몇 주 사이에 미국 통화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까진 다들 \"올해 6월에 금리 인하가 시작될 것\"이라고 했는데, 이젠 \"아직 멀었다\"는 사람이 많아졌죠.\n"
            + "<br>\n"
            + "지난 16일(현지시간) 제롬 파월 미국 연방준비제도(Fed·연준) 의장은 사실상 6월 금리 인하가 무산됐음을 인정했어요. 파월 의장은 \"최근 데이터는 (금리 인하에 대한) 확신을 주지 못했고, 그런 확신을 얻는 데에는 예상보다 더 오랜 시간이 걸릴 것\"이라고 말했어요.\n"
            + "<br>\n"
            + "이제 미국의 기준금리인하는 9월 이후에 가능하다는 전망이 우세하고, 인하 시점을 내년으로 보는 사람도 꽤 많아졌어요. 오히려 기준금리를 지금보다 조금 더 올릴 수 있다는 전망까지 나오고 있어요.\n"
            + "<br>\n"
            + "왜 갑자기 분위기가 바뀐 걸까요? 경제 전문가들이 대부분 이런 변화를 예상하지 못했던 이유는 뭘까요?\n"
            + "<br>\n"
            + "예상보다 너무 좋은 미국 경제미국이 기준금리 인하에 나서지 못하고 있는 이유는 예상을 뛰어넘는 미국의 경제 호황이에요. 미국은 지난 2022년부터 높은 물가 상승률을 낮추기 위해 빠르게 기준금리를 인상했어요. 불과 1년 반도 안 되는 기간에 0.25%에서 5.5%로 급격히 올렸죠. 그리고 그 여파로 경제가 침체를 겪을 것으로 봤어요. 긴축적 통화 정책은 대체로 경기 둔화를 이끌기 때문이에요.\n"
            + "<br>\n"
            + "하지만 비슷하게 긴축에 나섰던 다른 주요국들이 경제 침체를 겪는 동안, 미국은 상대적으로 경제 호황을 누리고 있어요. 빠른 기준금리 인상에 따라 경착륙(심한 경기 침체), 연착륙(약한 경기 침체), 스태그플레이션 등 여러 침체 시나리오를 제시했던 전문가들의 예상이 모두 빗나간 거예요.\n"
            + "<br>\n"
            + "국제통화기금(IMF)은 지난 16일 미국의 올해 경제성장률 예상치를 기존 2.1%에서 2.7%로 0.6%포인트나 상향 조정했어요. 유로화를 사용하는 유로존(0.8%)보다 3배 이상 높은 수치예요. IMF 또한 작년까지 미국의 경기 침체를 예상해 왔는데, 결국 지난 예상이 틀렸다는 걸 인정한 셈이에요.\n"
            + "<br>\n"
            + "https://imgnews.pstatic.net/image/origin/366/2022/03/17/1160000.jpg\n"
            + "미국 경제의 3대 미스터리?\n"
            + "\n"
            + "이런 미국 경제의 흐름을 두고 전문가들은 \"미스터리\"라는 표현까지 쓰기 시작했어요. 그만큼 기존의 통념과 분석을 뒤집는 현상들이 벌어지고 있다는 거죠. 미국 경제 호황이 뒤집은 전통적 관점들을 간단히 정리해 보면 다음과 같아요.\n"
            + "\n"
            + "① 깨져버린 물가·실업률 반비례 공식\n"
            + "② ‘장단기 금리 역전’에도 호황\n"
            + "③ 원격근무 확산에도 높아진 생산성\n"
            + "<br>\n"
            + "보는 사람들은 지금의 경제 호황을 팬데믹이라는 특수한 상황 이후에 이어지는 일시적 현상으로 해석해요. 미국 경제는 전반적으로 호조를 보이고 있지만, 실제로 불안한 지표들이 존재하는 건 사실이에요. 급격한 금리 인상에도 불구하고 전례 없는 호황을 맞은 미국 경제. 세계 금융산업 중심인 월스트리트의 기업들조차도 섣불리 앞날을 전망하지 못하는 분위기인데요. 미스터리한 미국 경제의 성장세는 이대로 계속될 수 있을까요?\n"
            + "<br>";

        NewsLetter newsLetter = NewsLetter.builder()
            .content(Content.from(content))
            .build();


        // when
        List<Block> blocks = parseNewsLetterUseCase.parseContentToBlocks(newsLetter);

        // then
        Assertions.assertThat(blocks.size()).isGreaterThan(1);
        assertEquals("paragraph", blocks.get(0).getType());
    }
}