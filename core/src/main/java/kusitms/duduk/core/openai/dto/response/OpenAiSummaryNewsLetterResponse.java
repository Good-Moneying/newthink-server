package kusitms.duduk.core.openai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenAiSummaryNewsLetterResponse implements Serializable{
    private String keywords;
    private String category;
    private String content;
}
