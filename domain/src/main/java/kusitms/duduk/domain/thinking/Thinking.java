package kusitms.duduk.domain.thinking;

import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.global.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Builder(toBuilder = true)
public class Thinking {

    private Id id;
    private Id userId;
    private Id newsLetterId;
    private Thumbnail thumbnail;
    private Sentence comment;
    private Keywords keywords;
    private Sentence summarizedComment;
    private boolean isCloudExist;
    @Default
    private List<Sentence> thinkingCloud = new ArrayList<>();

    public void createThinkingCloud(List<String> sentences) {
        Assert.isTrue(sentences.size() == 5, "생각 구름은 5개의 문장으로 구성되어야 합니다.");
        this.thinkingCloud = sentences.stream()
            .map(Sentence::from)
            .toList();

        this.isCloudExist = true;
    }

}
