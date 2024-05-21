package kusitms.duduk.application.openai.service;

import kusitms.duduk.core.ai.dto.response.ParsedAiContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParsingAiContentCommand {

    public ParsedAiContentResponse getParsingResult(String response){
        log.info("Parsing AI Content Response: {}", response);

        String content = response.substring(response.indexOf("본문: ") + 4, response.indexOf("\n제목:")).trim();
        String headline = response.substring(response.indexOf("제목: ") + 4, response.indexOf("\n키워드: ")).trim();
        String keywords = response.substring(response.indexOf("키워드: ") + 5, response.indexOf("\n카테고리:")).trim();
        String category  =response.substring(response.indexOf("카테고리: ") + 6).trim();

        return ParsedAiContentResponse.builder()
                .title(headline)
                .content(content)
                .keywords(keywords)
                .category(category)
                .build();
    }
}