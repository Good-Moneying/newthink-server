package kusitms.duduk.domain.term.vo;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Name {

    private final String value;

    private Name(String name) {
        Assert.notNull(name, "name must not be null");
        Assert.isTrue(name.length() >= 1 && name.length() <= 30,
            "name length must be between 1 and 30 characters");
        this.value = name;
    }

    public static Name from(String name) {
        return new Name(name);
    }
}
