package kusitms.duduk.core.attendance.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder(toBuilder = true)
public record WeeklyAttendanceResponse(List<DailyAttendant> attendants) {

    public static WeeklyAttendanceResponse of(List<LocalDate> attendanceDates) {
        Map<DayOfWeek, Boolean> attendanceMap = Stream.of(DayOfWeek.values())
            .collect(Collectors.toMap(day -> day, day -> false));  // 모든 요일을 false로 초기화

        attendanceDates.forEach(
            date -> attendanceMap.put(date.getDayOfWeek(), true));  // 출석한 날짜 업데이트

        List<DailyAttendant> dailyAttendants = attendanceMap.entrySet().stream()
            .map(entry -> new DailyAttendant(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        return WeeklyAttendanceResponse.builder()
            .attendants(dailyAttendants)
            .build();
    }

    @Data
    @AllArgsConstructor
    @Getter
    public static class DailyAttendant {

        public DayOfWeek dayOfWeek;
        public boolean isAttendant;
    }
}
