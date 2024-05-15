package kusitms.duduk.application.user.event;

import kusitms.duduk.core.user.port.input.AttendUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginEventListener {

    private final AttendUserUseCase attendUserUseCase;

    @EventListener
    public void attendUser(LoginUserEvent event) {
        attendUserUseCase.attend(event.getEmail());
    }
}
