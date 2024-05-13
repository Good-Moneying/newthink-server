package kusitms.duduk.batch.dto.openai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIResponse {

    private List<OpenAIResponseContent> choices;

    public String getContent(){
        return choices.get(0).getMessage().getContent();
    }
}
