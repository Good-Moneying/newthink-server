package kusitms.duduk.application.attendant.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.core.attendant.dto.WeeklyAttendantResponse;
import kusitms.duduk.core.attendant.port.input.DateProvider;
import kusitms.duduk.core.attendant.port.output.LoadAttendantPort;
import kusitms.duduk.core.attendant.port.output.SaveAttendantPort;
import kusitms.duduk.core.user.port.input.AttendUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendUserCommand implements AttendUserUseCase {

    private final SaveAttendantPort saveAttendantPort;
    private final LoadAttendantPort loadAttendantPort;

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
    public WeeklyAttendantResponse calculateAttendance(final String email) {
        LocalDate startDate = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate endDate = LocalDate.now().with(DayOfWeek.SUNDAY);

        List<LocalDate> attendanceDates = loadAttendantPort.findAttendantDatesByEmailAndDateBetween(
            email, startDate, endDate);

        return WeeklyAttendantResponse.of(attendanceDates);
    }
}
