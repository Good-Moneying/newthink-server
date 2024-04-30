package kusitms.duduk.domain.user.dto;

import java.time.LocalDate;

public record CreateUserRequest(String email,
		String nickname,
		String gender,
		LocalDate birthDay,
		String category) {

}