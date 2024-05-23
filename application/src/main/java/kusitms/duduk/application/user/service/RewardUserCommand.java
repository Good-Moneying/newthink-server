package kusitms.duduk.application.user.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import kusitms.duduk.common.exception.ErrorMessage;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.user.port.input.RewardUserUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RewardUserCommand implements RewardUserUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    @Override
    public void reward(Long userId, int rewardAmount) {
        User user = loadUserById(userId);

        user.addReward(rewardAmount);
        updateUserPort.update(user);
    }

    private User loadUserById(Long userId) {
        User user = loadUserPort.findById(userId)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage() + userId));
        return user;
    }
}
