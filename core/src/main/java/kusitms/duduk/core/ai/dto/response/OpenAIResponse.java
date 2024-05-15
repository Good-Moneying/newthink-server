package kusitms.duduk.core.ai.dto.response;

import java.util.List;

public record OpenAIResponse (List<OpenAIResponseContent> choices){

    public String getContent(){
        return choices.get(0).message().content();
    }
}
