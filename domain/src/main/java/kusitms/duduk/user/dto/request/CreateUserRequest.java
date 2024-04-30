package kusitms.duduk.user.dto.request;

import java.time.LocalDate;

public record CreateUserRequest(String email,
		String nickname,
		String gender,
		LocalDate birthDay,
		String category) {

}
