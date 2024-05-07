package kusitms.duduk.domain.newsletter.vo;

import lombok.Getter;

@Getter
public class Thumbnail {

    private String url;

    private Thumbnail(String url) {
        this.url = url;
    }

    public static Thumbnail from(String url) {
        return new Thumbnail(url);
    }
}
