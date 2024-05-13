package kusitms.duduk.application.attendant.persistence;

import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.application.attendant.persistence.entity.AttendantJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.attendant.port.output.LoadAttendantPort;
import kusitms.duduk.core.attendant.port.output.SaveAttendantPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class AttendantPersistenceAdapter implements SaveAttendantPort, LoadAttendantPort {

    private final AttendantRepository attendantRepository;
    private final AttendanceRepositoryCustom attendanceRepositoryCustom;

    @Override
    public void save(LocalDate today, String email) {
        attendantRepository.save(AttendantJpaEntity.builder()
            .date(today)
            .email(email)
            .build());
    }

    @Override
    public boolean isAttendedToday(String email) {
        return attendantRepository.existsByEmailAndDate(email, LocalDate.now());
    }

    @Override
    public List<LocalDate> findAttendantDatesByEmailAndDateBetween(String email, LocalDate startDate,
        LocalDate endDate) {
        return attendanceRepositoryCustom.findAttendanceBetweenStartDateAndEndDate(email, startDate, endDate);
    }
}
