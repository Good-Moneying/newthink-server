package kusitms.duduk.application.term.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import kusitms.duduk.application.term.persistence.entity.QTermJpaEntity;
import kusitms.duduk.application.term.persistence.entity.TermJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TermRepositoryImpl implements TermRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<TermJpaEntity> findLatestTerm() {
        QTermJpaEntity qTerm = QTermJpaEntity.termJpaEntity;

        TermJpaEntity latestTerm = queryFactory
            .selectFrom(qTerm)
            .orderBy(qTerm.createdAt.desc())
            .fetchFirst();

        return Optional.ofNullable(latestTerm);
    }
}
