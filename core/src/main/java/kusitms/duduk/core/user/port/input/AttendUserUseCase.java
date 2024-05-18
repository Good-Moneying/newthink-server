package kusitms.duduk.core.user.port.input;

import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse;

public interface AttendUserUseCase {

    void attend(final String email);
    WeeklyAttendanceResponse calculateAttendance(final String email);
}
