package kusitms.duduk.application.user.service;

import kusitms.duduk.core.user.dto.UserDtoMapper;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserEmailRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserNicknameRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import kusitms.duduk.core.user.port.input.ValidateDuplicatedUserQuery;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegisterUserCommand implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final ValidateDuplicatedUserQuery validateDuplicatedUserQuery;
    private final UserDtoMapper userDtoMapper;

    @Override
    @Transactional
    public UserResponse register(CreateUserRequest request) {
        User user = userDtoMapper.toDomain(request);

        validateDuplicatedUserQuery.validateDuplicatedEmail(new ValidateUserEmailRequest(user.getEmail().getValue()));
        validateDuplicatedUserQuery.validateDuplicatedNickname(new ValidateUserNicknameRequest(user.getNickname().getValue()));

        User savedUser = saveUserPort.create(user);
        log.info("유저가 성공적으로 저장되었습니다.\n "
            + "Email : {}\n"
            + "Nickname : {}", savedUser.getEmail().getValue(), savedUser.getNickname().getValue());

        return userDtoMapper.toDto(savedUser);
    }
}