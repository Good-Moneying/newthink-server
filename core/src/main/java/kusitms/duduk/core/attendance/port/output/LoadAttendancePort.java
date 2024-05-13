package kusitms.duduk.core.attendance.port.output;

import java.time.LocalDate;
import java.util.List;

public interface LoadAttendancePort {

    boolean isAttendedToday(String email);

    List<LocalDate> findAttendantDatesByEmailAndDateBetween(String email, LocalDate startDate,
        LocalDate endDate);
}
