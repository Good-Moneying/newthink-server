package kusitms.duduk.batch.utils;

import kusitms.duduk.batch.util.ParsingAiContentUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("ParsingAiContent 테스트")
public class ParsingAiContentUtilsTest {

    @Autowired
    private ParsingAiContentUtils parsingAiContent;

    @Test
    public void 정상적인_입력시_결과를_잘_출력한다(){

        String message = "";

        //parsingAiContent.getParingResult();
    }

}
