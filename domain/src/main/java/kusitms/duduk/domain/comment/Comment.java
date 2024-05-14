package kusitms.duduk.domain.comment;

import java.time.LocalDateTime;
import kusitms.duduk.domain.comment.vo.Content;
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
    private LocalDateTime createdAt;
}
