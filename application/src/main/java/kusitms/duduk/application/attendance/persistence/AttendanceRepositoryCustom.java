package kusitms.duduk.application.attendance.persistence;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepositoryCustom {

    List<LocalDate> findAttendanceBetweenStartDateAndEndDate(String email, LocalDate startDate, LocalDate endDate);
}
