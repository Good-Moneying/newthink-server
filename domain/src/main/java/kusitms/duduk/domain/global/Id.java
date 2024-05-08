package kusitms.duduk.domain.global;

import java.util.Objects;
import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class Id {

    private final Long value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Id id)) {
            return false;
        }
        return Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
