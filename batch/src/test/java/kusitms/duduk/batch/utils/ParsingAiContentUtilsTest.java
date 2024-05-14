package kusitms.duduk.batch.utils;

import kusitms.duduk.batch.dto.openai.parsing.ParsedAiContent;
import kusitms.duduk.batch.util.ParsingAiContentUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayName("ParsingAiContent 테스트")
public class ParsingAiContentUtilsTest {

    @Autowired
    private ParsingAiContentUtils parsingAiContent;

    @Test
    public void 정상적인_입력시_결과를_잘_출력한다(){

        String message = "본문: 한국은행이 발표한 자료에 따르면 간편송금 이용금액이 하루 2000억원을 넘어섰다. 이용금액은 지난해 대비 60.7% 증가한 2005억원으로 나타났고, 이용건수도 34.8% 증가한 218만건이었다. 한은은 카카오페이, 토스 등이 서비스를 제공함에 따라 시장 경쟁이 치열해지면서 이용규모가 크게 증가했다고 분석했다. 마케팅 비용으로 1000억원 이상을 소비한 업체도 있었다. \n"
                        + "제목: 하루 2000억' 판 커지는 간편송금 시장 \n"
                        + "키워드: 한국은행, 간편송금, 카카오페이\n"
                        + "카테고리: FINANCE";

        ParsedAiContent parsedAiContent = parsingAiContent.getParingResult(message);

        ParsedAiContent parsedAiContent1 = ParsedAiContent.builder()
                .headline("하루 2000억' 판 커지는 간편송금 시장")
                .content("한국은행이 발표한 자료에 따르면 간편송금 이용금액이 하루 2000억원을 넘어섰다. 이용금액은 지난해 대비 60.7% 증가한 2005억원으로 나타났고, 이용건수도 34.8% 증가한 218만건이었다. 한은은 카카오페이, 토스 등이 서비스를 제공함에 따라 시장 경쟁이 치열해지면서 이용규모가 크게 증가했다고 분석했다. 마케팅 비용으로 1000억원 이상을 소비한 업체도 있었다.")
                .keywords("한국은행, 간편송금, 카카오페이")
                .category("FINANCE")
                .build();

        System.out.println(parsedAiContent.toString());

        assertThat(parsedAiContent).isEqualToComparingFieldByField(parsedAiContent1);
    }
}
