package kusitms.duduk.core.user.port.input;

import kusitms.duduk.core.attendant.dto.WeeklyAttendantResponse;

public interface AttendUserUseCase {

    void attend(final String email);
    WeeklyAttendantResponse calculateAttendance(final String email);
}
