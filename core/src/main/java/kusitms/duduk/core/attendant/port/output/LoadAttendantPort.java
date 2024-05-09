package kusitms.duduk.core.attendant.port.output;

import java.time.LocalDate;

public interface LoadAttendantPort {
    boolean isAttendedToday(String email);

    // todo : 한 주에 몇번 출석 체크 했는지도 검증 가능
}
