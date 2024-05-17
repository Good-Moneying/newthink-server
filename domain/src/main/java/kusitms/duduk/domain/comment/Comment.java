package kusitms.duduk.domain.comment;

import java.time.LocalDateTime;
import kusitms.duduk.domain.global.Sentence;
import kusitms.duduk.domain.comment.vo.Perspective;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Comment {

    private Id id;
    private Id userId;
    private Id newsLetterId;
    boolean isPrivate;
    private Sentence sentence;
    private Sentence summarizedSentence;
    private Perspective perspective;
    private Count likeCount;
    private LocalDateTime createdAt;

    public void addSummarizedContent(String summarize) {
        this.summarizedSentence = Sentence.from(summarize);
    }
}
