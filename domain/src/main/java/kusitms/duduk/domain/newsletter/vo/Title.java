package kusitms.duduk.domain.newsletter.vo;

import lombok.Getter;

@Getter
public class Title {

    private String title;

    private Title(String title) {
        this.title = title;
    }

    public static Title from(String title) {
        return new Title(title);
    }

    public void revise(String title) {
        this.title = title;
    }
}
