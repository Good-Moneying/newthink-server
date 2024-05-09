package kusitms.duduk.application.attendence.service;

import java.time.LocalDate;
import kusitms.duduk.core.attendant.port.output.LoadAttendantPort;
import kusitms.duduk.core.attendant.port.output.SaveAttendantPort;
import kusitms.duduk.core.user.port.input.AttendUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendUserCommand implements AttendUserUseCase {

    private final SaveAttendantPort saveAttendantPort;
    private final LoadAttendantPort loadAttendantPort;

    @Override
    public void attend(String email) {
        if (!loadAttendantPort.isAttendedToday(email)) {
            log.info("attend user: {}", email);
            saveAttendantPort.save(LocalDate.now(), email);
        }
    }
}
