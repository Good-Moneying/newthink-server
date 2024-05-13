package kusitms.duduk.core.attendance.port.output;

import java.time.LocalDate;

public interface SaveAttendancePort {
    void save(LocalDate date, String email);
}
