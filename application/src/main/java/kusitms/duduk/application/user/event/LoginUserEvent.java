package kusitms.duduk.application.user.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LoginUserEvent extends ApplicationEvent {

    private final String email;

    public LoginUserEvent(Object source, String email) {
        super(source);
        this.email = email;
    }
}
