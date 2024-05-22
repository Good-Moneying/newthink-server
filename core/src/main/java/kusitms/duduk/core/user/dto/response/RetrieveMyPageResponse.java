package kusitms.duduk.core.user.dto.response;

import java.util.List;
import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse;
import kusitms.duduk.domain.global.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
public record RetrieveMyPageResponse(String level, String profileUrl, String nickname, int reward, int follower, int followee,
		     WeeklyAttendanceResponse attendances,
		     List<ArchiveNewsLetterCount> counts) {

    @Data
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class ArchiveNewsLetterCount {

        private String categoryName;
        private int count;
    }
}
