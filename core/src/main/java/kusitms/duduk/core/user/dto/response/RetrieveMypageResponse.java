package kusitms.duduk.core.user.dto.response;

import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse;
import lombok.Builder;

@Builder(toBuilder = true)
public record RetrieveMypageResponse(String nickname, int reward,
		     WeeklyAttendanceResponse attendants) {

}
