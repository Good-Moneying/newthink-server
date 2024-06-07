package kusitms.duduk.application.survey.persistence;

import java.util.Optional;
import kusitms.duduk.application.survey.persistence.entity.SurveyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SurveyRepository extends JpaRepository<SurveyJpaEntity, Long> {

    @Query("SELECT s FROM SurveyJpaEntity s ORDER By s.createdAt DESC LIMIT 1")
    Optional<SurveyJpaEntity> findTodaySurvey();
}
