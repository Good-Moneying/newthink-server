package kusitms.duduk.application.attendence.persistence;

import java.time.LocalDate;
import kusitms.duduk.application.attendence.persistence.entity.AttendantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendantRepository extends JpaRepository<AttendantJpaEntity, Long> {

    boolean existsByEmailAndDate(String email, LocalDate date);
}
