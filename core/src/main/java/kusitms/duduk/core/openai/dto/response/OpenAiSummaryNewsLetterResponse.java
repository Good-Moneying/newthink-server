package kusitms.duduk.core.openai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class OpenAiSummaryNewsLetterResponse implements Serializable{
    private String keywords;
    private String category;
    private String content;
}
