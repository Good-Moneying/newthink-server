package kusitms.duduk.application.attendant.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import kusitms.duduk.application.attendant.persistence.entity.QAttendantJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<LocalDate> findAttendanceBetweenStartDateAndEndDate(String email, LocalDate startDate,
        LocalDate endDate) {
        QAttendantJpaEntity attendant = QAttendantJpaEntity.attendantJpaEntity;

        // QueryDSL로 쿼리 구성하여 LocalDate 리스트 반환
        return new JPAQueryFactory(entityManager)
            .select(attendant.date)
            .from(attendant)
            .where(attendant.email.eq(email)
                .and(attendant.date.between(startDate, endDate)))
            .fetch();
    }
}
