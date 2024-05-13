package kusitms.duduk.batch.dto.openai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIRequest {
    private String model;
    private List<OpenAIMessages> messages;

    public void addMessage(OpenAIMessages message) {
        this.messages.add(message);
    }
}
