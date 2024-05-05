package kusitms.duduk.domain.user;

import kusitms.duduk.domain.user.vo.Email;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 유저_객체를_생성한다() {
        // given
        Email email = Email.of("test@gmail.com");

        // when
        User user = User.create(

        );

        // then
    }
}
