package kusitms.duduk.application.attendance.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse;
import kusitms.duduk.core.attendance.port.input.DateProvider;
import kusitms.duduk.core.attendance.port.output.LoadAttendancePort;
import kusitms.duduk.core.attendance.port.output.SaveAttendancePort;
import kusitms.duduk.core.user.port.input.AttendUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendUserCommand implements AttendUserUseCase {

    private final SaveAttendancePort saveAttendantPort;
    private final LoadAttendancePort loadAttendantPort;

    @Qualifier("systemDateProvider")
    private final DateProvider dateProvider;

    @Override
    public void attend(final String email) {
        if (!loadAttendantPort.isAttendedToday(email)) {
            log.info("attend user: {}", email);
            saveAttendantPort.save(dateProvider.now(), email);
        }
    }

    @Override
    public WeeklyAttendanceResponse calculateAttendance(final String email) {
        LocalDate startDate = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate endDate = LocalDate.now().with(DayOfWeek.SUNDAY);

        List<LocalDate> attendanceDates = loadAttendantPort.findAttendantDatesByEmailAndDateBetween(
            email, startDate, endDate);

        return WeeklyAttendanceResponse.of(attendanceDates);
    }
}
