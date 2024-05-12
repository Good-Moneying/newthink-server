package kusitms.duduk.application.newsletter.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.application.newsletter.persistence.entity.QNewsLetterJpaEntity;
import kusitms.duduk.domain.newsletter.vo.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NewsLetterRepositoryImpl implements NewsLetterRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<NewsLetterJpaEntity> findLatestNewsLetter() {
        QNewsLetterJpaEntity qNewsLetter = QNewsLetterJpaEntity.newsLetterJpaEntity;

        NewsLetterJpaEntity latestNewsLetter = queryFactory
            .selectFrom(qNewsLetter)
            .where(qNewsLetter.type.eq(Type.EDITOR))
            .orderBy(qNewsLetter.createdAt.desc())
            .fetchFirst();

        return Optional.ofNullable(latestNewsLetter);
    }

}
