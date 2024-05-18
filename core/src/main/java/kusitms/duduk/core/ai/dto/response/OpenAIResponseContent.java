package kusitms.duduk.core.ai.dto.response;

import lombok.Getter;

public record OpenAIResponseContent (Message message){

    public record Message (String role, String content){
    }
}
