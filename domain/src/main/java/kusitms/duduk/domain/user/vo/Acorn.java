package kusitms.duduk.domain.user.vo;

import lombok.Getter;

@Getter
public class Acorn {

    private int count;

    private Acorn(int count) {
        this.count = count;
    }

    public static Acorn initial() {
        return new Acorn(0);
    }

    public static Acorn of(int acornCount) {
        return new Acorn(acornCount);
    }

    public void addForAttendence() {
        this.count += 1;
    }

    public void addForComment() {
        this.count += 2;
    }
}