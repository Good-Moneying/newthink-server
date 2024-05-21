package kusitms.duduk.core.user.port.input;

public interface RewardUserUseCase {

    void reward(final Long userId, final int rewardAmount);
}
