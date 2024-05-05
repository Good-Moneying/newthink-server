package kusitms.duduk.domain.user.vo;

import java.util.Objects;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Nickname {

    private String value;

    private Nickname(String nickname) {
        Assert.notNull(nickname, "닉네임은 null이 될 수 없습니다.");
        this.value = nickname;
    }

    public static Nickname of(String nickname) {
        return new Nickname(nickname);
    }

    public void changeNickname(String nickname) {
        this.value = nickname;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Nickname other = (Nickname) obj;
        return Objects.equals(this.value, other.value);
    }

}
