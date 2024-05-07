package kusitms.duduk.domain.global;

import lombok.Getter;

@Getter
public class Count {

    private static final int ZERO = 0;
    private int Count;

    private Count(int Count) {
        this.Count = Count;
    }

    public static Count initial() {
        return new Count(ZERO);
    }

    public static Count from(int Count) {
        return new Count(Count);
    }

    public void increase() {
        this.Count++;
    }

    public void decrease() {
        this.Count--;
    }
}
