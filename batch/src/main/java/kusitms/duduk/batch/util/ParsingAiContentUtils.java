package kusitms.duduk.batch.util;

import kusitms.duduk.batch.dto.openai.parsing.ParsedAiContent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class ParsingAiContentUtils {

    private String headline;
    private String content;
    private String stock;
    private String quiz;
    private final List<String> options = new ArrayList<>();
    private Integer answer;

    public ParsedAiContent getParingResult(String response){
        StringTokenizer tokenizer = new StringTokenizer(response, ":");

        List<String> tokens = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            tokens.add(token);
        }

        for(int i =0; i < tokens.size(); i++){
            if(tokens.get(i).equals(":")){
                String token = tokens.get(i-1).trim();
                switch (token){
                    case "제목":
                        headline = tokens.get(i+1).trim();
                    case "본문":
                        content = tokens.get(i+1).trim();
                    case "관련주":
                        stock = tokens.get(i+1).trim();
                    case "퀴즈":
                        quiz = tokens.get(i+1).trim();
                    case "1": case "2": case "3": case "4": case "5":
                        options.add(tokens.get(i+1).trim());
                    case "정답" :
                        answer = Integer.valueOf(tokens.get(i+1).trim());
                }
            }
        }

        return ParsedAiContent.builder()
                .headline(headline)
                .content(content)
                .stock(stock)
                .quiz(quiz)
                .options(options)
                .answer(answer)
                .build();
    }
}
