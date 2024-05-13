package kusitms.duduk.application.attendant.persistence;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepositoryCustom {

    List<LocalDate> findAttendanceBetweenStartDateAndEndDate(String email, LocalDate startDate, LocalDate endDate);
}
