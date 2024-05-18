package kusitms.duduk.core.openai.dto.request;

import java.util.List;

public record OpenAiSummaryRequest(String model, List<Message> messages) {

    public OpenAiSummaryRequest(String comment) {
        this("gpt-4", List.of(
            new Message("system", "You are a helpful assistant."),
            new Message("user", comment)
        ));
    }

    public record Message(String role, String content) {

    }
}
