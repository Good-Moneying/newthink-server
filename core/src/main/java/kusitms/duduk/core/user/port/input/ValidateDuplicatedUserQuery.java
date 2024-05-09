package kusitms.duduk.core.user.port.input;

import kusitms.duduk.core.user.dto.request.ValidateUserEmailRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserNicknameRequest;

public interface ValidateDuplicatedUserQuery {

    void validateDuplicatedNickname(ValidateUserNicknameRequest nickname);

    void validateDuplicatedEmail(ValidateUserEmailRequest email);
}
