package kusitms.duduk.application.attendance.persistence;

import java.time.LocalDate;
import kusitms.duduk.application.attendance.persistence.entity.AttendanceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceJpaEntity, Long> {

    boolean existsByEmailAndDate(String email, LocalDate date);
}
