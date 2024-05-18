package kusitms.duduk.core.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record OpenAIMessages (String role, String content){

}
