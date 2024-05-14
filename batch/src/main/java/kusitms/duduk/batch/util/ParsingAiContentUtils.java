package kusitms.duduk.batch.util;

import kusitms.duduk.batch.dto.openai.parsing.ParsedAiContent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class ParsingAiContentUtils {

    public ParsedAiContent getParingResult(String response){
        String content = response.substring(response.indexOf("본문: ") + 4, response.indexOf("\n제목:")).trim();
        String headline = response.substring(response.indexOf("제목: ") + 4, response.indexOf("\n키워드: ")).trim();
        String keywords = response.substring(response.indexOf("키워드: ") + 5, response.indexOf("\n카테고리:")).trim();
        String category  =response.substring(response.indexOf("카테고리: ") + 6).trim();

        return ParsedAiContent.builder()
                .headline(headline)
                .content(content)
                .keywords(keywords)
                .category(category)
                .build();
    }
}
