package kusitms.duduk.core.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
public record OpenAIRequest(String model, List<OpenAIMessages> messages) { ;

    public void addMessage(OpenAIMessages message) {
        this.messages.add(message);
    }
}
