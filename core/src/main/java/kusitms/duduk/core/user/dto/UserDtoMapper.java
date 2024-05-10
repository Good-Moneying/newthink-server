package kusitms.duduk.core.user.dto;

import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.user.User;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;

@Mapper
public class UserDtoMapper {

    public User toDomain(CreateUserRequest request) {
        return User.builder()
            .email(Email.of(request.email()))
            .nickname(Nickname.of(request.nickname()))
            .refreshToken(RefreshToken.of(request.refreshToken()))
            .gender(Gender.from(request.gender()))
            .birthday(request.birthDay())
            .role(Role.USER)
            .provider(Provider.from(request.provider()))
            .category(Category.from(request.category()))
            .goal(Goal.from(request.goal()))
            .build();
    }

    public UserResponse toDto(User save) {
        return UserResponse.builder()
            .email(save.getEmail().getValue())
            .nickname(save.getNickname().getValue())
            .build();
    }
}
