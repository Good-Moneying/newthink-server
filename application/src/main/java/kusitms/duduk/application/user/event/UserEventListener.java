package kusitms.duduk.application.user.event;

import kusitms.duduk.application.comment.event.CreateCommentEvent;
import kusitms.duduk.application.thinking.event.CreateThinkingEvent;
import kusitms.duduk.core.user.port.input.AttendUserUseCase;
import kusitms.duduk.core.user.port.input.RewardUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserEventListener {

    private final AttendUserUseCase attendUserUseCase;
    private final RewardUserUseCase rewardUserUseCase;

    @EventListener
    public void attendUser(LoginUserEvent event) {
        attendUserUseCase.attend(event.getEmail());
    }

    @EventListener
    public void addReward(CreateCommentEvent event) {
        rewardUserUseCase.reward(event.getUserId(), 1);
    }

    @EventListener
    public void addReward(CreateThinkingEvent event) {
        rewardUserUseCase.reward(event.getUserId(), 1);
    }
}
