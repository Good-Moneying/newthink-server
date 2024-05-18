package kusitms.duduk.domain.global;

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
