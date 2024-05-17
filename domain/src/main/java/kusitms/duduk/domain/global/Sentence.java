package kusitms.duduk.domain.global;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Sentence {

    private String value;

    public static Sentence from(String value) {
        return new Sentence(value);
    }
}
