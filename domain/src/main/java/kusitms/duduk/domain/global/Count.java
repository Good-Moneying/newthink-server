package kusitms.duduk.domain.global;

import lombok.Getter;

@Getter
public class Count {

    private static final int ZERO = 0;
    private static final int REGISTER_REWARD = 3;
    private int Count;

    private Count(int Count) {
        this.Count = Count;
    }

    public static Count initial() {
        return new Count(ZERO);
    }

    public static Count register() {
        return new Count(REGISTER_REWARD);
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

    public void addAmount(int amount) {
        this.Count += amount;
    }
}
