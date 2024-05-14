package kusitms.duduk.domain.comment;

import java.time.LocalDateTime;
import kusitms.duduk.domain.comment.vo.Content;
import kusitms.duduk.domain.comment.vo.Perspective;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Comment {

    private Id id;
    private User user;
    private NewsLetter newsLetter;
    private Content content;
    private Content summarizedContent;
    private Perspective perspective;
    private Count likeCount;
    private LocalDateTime createdAt;
}
