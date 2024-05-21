package kusitms.duduk.core.openai.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public record OpenAiResponse(List<Choice> choices) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Choice(Message message) {

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Message(String content) {

    }

    public String extractContent() {
        return choices.stream()
            .map(Choice::message)
            .map(Message::content)
            .findFirst()
            .orElse(null);
    }
}