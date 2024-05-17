package kusitms.duduk.core.user.dto.response;

import java.util.List;
import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse;
import kusitms.duduk.domain.global.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
public record RetrieveMyPageResponse(String nickname, int reward,
		     WeeklyAttendanceResponse attendances,
		     List<ArchiveNewsLetterCount> counts) {

    @Data
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class ArchiveNewsLetterCount {

        private Category category;
        private int count;
    }
}
