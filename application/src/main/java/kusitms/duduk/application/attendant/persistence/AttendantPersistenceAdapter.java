package kusitms.duduk.application.attendant.persistence;

import java.time.LocalDate;
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
}
