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
public record WeeklyAttendanceResponse(List<DailyAttendance> data) {

    public static WeeklyAttendanceResponse of(List<LocalDate> attendanceDates) {
        Map<DayOfWeek, Boolean> attendanceMap = Stream.of(DayOfWeek.values())
            .collect(Collectors.toMap(day -> day, day -> false));  // 모든 요일을 false로 초기화

        attendanceDates.forEach(
            date -> attendanceMap.put(date.getDayOfWeek(), true));  // 출석한 날짜 업데이트

        List<DailyAttendance> dailyAttendances = attendanceMap.entrySet().stream()
            .map(entry -> new DailyAttendance(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        return WeeklyAttendanceResponse.builder()
            .data(dailyAttendances)
            .build();
    }

    @Data
    @AllArgsConstructor
    @Getter
    public static class DailyAttendance {

        public DayOfWeek dayOfWeek;
        public boolean isAttendant;

        public static DailyAttendance of(DayOfWeek dayOfWeek, boolean isAttendant) {
            return new DailyAttendance(dayOfWeek, isAttendant);
        }
    }
}
