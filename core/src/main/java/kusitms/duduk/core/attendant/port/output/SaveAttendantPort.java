package kusitms.duduk.core.attendant.port.output;

import java.time.LocalDate;

public interface SaveAttendantPort {
    void save(LocalDate date, String email);
}
