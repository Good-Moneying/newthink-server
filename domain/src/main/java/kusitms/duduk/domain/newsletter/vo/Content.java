package kusitms.duduk.domain.newsletter.vo;

import lombok.Getter;

@Getter
public class Content {

    private String content;

    private Content(String content) {
        this.content = content;
    }

    public static Content from(String content) {
        return new Content(content);
    }

    public void revise(String content) {
        this.content = content;
    }
}
