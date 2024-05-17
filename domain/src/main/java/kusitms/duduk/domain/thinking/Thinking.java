package kusitms.duduk.domain.thinking;

import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.global.Thumbnail;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Thinking {

    private Id id;
    private Id userId;
    private Id newsLetterId;
    private Thumbnail thumbnail;
    private Sentence comment;
    private Sentence summarizedComment;
    private boolean isExist;
    @Default
    private List<Sentence> thinkingCloud = new ArrayList<>();

}
