package kusitms.duduk.core.user.dto;

import java.util.ArrayList;
import java.util.stream.Collectors;
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
            .email(Email.from(request.email()))
            .nickname(Nickname.from(request.nickname()))
            .refreshToken(RefreshToken.of(request.refreshToken()))
            .gender(Gender.from(request.gender()))
            .birthday(request.birthDay())
            .role(Role.USER)
            .provider(Provider.from(request.provider()))
            .category(Category.from(request.category()))
            .goal(Goal.from(request.goal()))
            .archives(new ArrayList<>())
            .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
            .email(user.getEmail().getValue())
            .nickname(user.getNickname().getValue())
            .archives(user.getArchives().stream()
                .map(archive -> archive.getId())
                .collect(Collectors.toList()))
            .build();
    }
}
