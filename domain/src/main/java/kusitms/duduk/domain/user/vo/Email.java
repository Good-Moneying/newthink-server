package kusitms.duduk.domain.user.vo;

import kusitms.duduk.domain.user.service.EmailValidator;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Objects;

@Getter
public class Email {

    private final String value;

    private Email(String email) {
        Assert.notNull(email, "이메일은 null이 될 수 없습니다.");
        EmailValidator.isValidEmail(email);
        this.value = email;
    }

    public static Email of(String mail) {
        return new Email(mail);
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
        Email other = (Email) obj;
        return Objects.equals(this.value, other.value);
    }
}
