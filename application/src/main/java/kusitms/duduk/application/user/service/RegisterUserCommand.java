package kusitms.duduk.application.user.service;

import kusitms.duduk.core.user.dto.UserDtoMapper;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegisterUserCommand implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserResponse register(CreateUserRequest request) {
        log.info("register() start\n");
        User user = userDtoMapper.toDomain(request);

        if (loadUserPort.existsUserByEmail(user.getEmail().getValue())) {
            throw new IllegalArgumentException("중복된 이메일로 회원 가입을 할 수 없습니다.");
        }

        return userDtoMapper.toDto(saveUserPort.save(user));
    }
}