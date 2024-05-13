package kusitms.duduk.core.user.dto.response;

import kusitms.duduk.core.attendant.dto.WeeklyAttendantResponse;
import lombok.Builder;

@Builder(toBuilder = true)
public record RetrieveMypageResponse(String nickname, int reward,
		     WeeklyAttendantResponse attendants) {

}
