package kusitms.duduk.domain.global;

import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class Id {

    private final Long value;
}
