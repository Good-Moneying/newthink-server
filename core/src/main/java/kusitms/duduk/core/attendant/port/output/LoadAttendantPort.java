package kusitms.duduk.core.attendant.port.output;

import java.time.LocalDate;
import java.util.List;

public interface LoadAttendantPort {

    boolean isAttendedToday(String email);

    List<LocalDate> findAttendantDatesByEmailAndDateBetween(String email, LocalDate startDate,
        LocalDate endDate);
}
