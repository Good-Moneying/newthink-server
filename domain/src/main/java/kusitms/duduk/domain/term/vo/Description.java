package kusitms.duduk.domain.term.vo;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Description {

    private final String value;

    private Description(String description) {
        Assert.notNull(description, "description must not be null");
        this.value = description;
    }

    public static Description from(String description) {
        return new Description(description);
    }
}
