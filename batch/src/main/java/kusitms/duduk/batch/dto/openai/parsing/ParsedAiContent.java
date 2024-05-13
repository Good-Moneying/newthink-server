package kusitms.duduk.batch.dto.openai.parsing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ParsedAiContent {

    private String headline;
    private String content;
    private String stock;
    private String quiz;
    private List<String> options;
    private Integer answer;

}
