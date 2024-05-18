package kusitms.duduk.application.attendance.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.application.attendance.persistence.entity.QAttendanceJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<LocalDate> findAttendanceBetweenStartDateAndEndDate(String email, LocalDate startDate,
        LocalDate endDate) {
        QAttendanceJpaEntity attendance = QAttendanceJpaEntity.attendanceJpaEntity;

        // QueryDSL로 쿼리 구성하여 LocalDate 리스트 반환
        return new JPAQueryFactory(entityManager)
            .select(attendance.date)
            .from(attendance)
            .where(attendance.email.eq(email)
                .and(attendance.date.between(startDate, endDate)))
            .fetch();
    }
}
