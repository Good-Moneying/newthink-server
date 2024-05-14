package kusitms.duduk.domain.comment.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Content {

    private String sentence;

    public static Content from(String sentence) {
        return new Content(sentence);
    }
}
