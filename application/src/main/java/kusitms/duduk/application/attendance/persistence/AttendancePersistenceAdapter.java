package kusitms.duduk.application.attendance.persistence;

import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.application.attendance.persistence.entity.AttendanceJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.attendance.port.output.LoadAttendancePort;
import kusitms.duduk.core.attendance.port.output.SaveAttendancePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class AttendancePersistenceAdapter implements SaveAttendancePort, LoadAttendancePort {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceRepositoryCustom attendanceRepositoryCustom;

    @Override
    public void save(LocalDate today, String email) {
        attendanceRepository.save(AttendanceJpaEntity.builder()
            .date(today)
            .email(email)
            .build());
    }

    @Override
    public boolean isAttendedToday(String email) {
        return attendanceRepository.existsByEmailAndDate(email, LocalDate.now());
    }

    @Override
    public List<LocalDate> findAttendantDatesByEmailAndDateBetween(String email, LocalDate startDate,
        LocalDate endDate) {
        return attendanceRepositoryCustom.findAttendanceBetweenStartDateAndEndDate(email, startDate, endDate);
    }
}
