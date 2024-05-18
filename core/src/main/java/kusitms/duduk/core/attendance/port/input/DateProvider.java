package kusitms.duduk.core.attendance.port.input;

import java.time.LocalDate;

public interface DateProvider {

    LocalDate now();
}
