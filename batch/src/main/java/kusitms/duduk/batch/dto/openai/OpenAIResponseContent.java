package kusitms.duduk.batch.dto.openai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIResponseContent {

    private Message message;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Message {
        private String role;
        private String content;
    }
}
