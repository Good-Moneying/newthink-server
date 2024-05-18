package kusitms.duduk.application.newsletter.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.application.newsletter.persistence.entity.QNewsLetterJpaEntity;
import kusitms.duduk.domain.global.Category;
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

    @Override
    public List<NewsLetterJpaEntity> findRealtimeTrendNewsLetters() {
        QNewsLetterJpaEntity newsLetter = QNewsLetterJpaEntity.newsLetterJpaEntity;

        return queryFactory.select(newsLetter)
            .from(newsLetter)
            .where(newsLetter.type.eq(Type.AI)) // 타입이 AI인 뉴스레터
            .orderBy(newsLetter.createdAt.desc()) // 생성일 기준 내림차순
            .limit(3) // 상위 3개만
            .fetch();
    }

    @Override
    public List<NewsLetterJpaEntity> findCustomizeNewsLetters(Category category) {
        QNewsLetterJpaEntity newsLetter = QNewsLetterJpaEntity.newsLetterJpaEntity;

        return queryFactory.select(newsLetter)
            .from(newsLetter)
            .where(newsLetter.category.eq(category)) // 카테고리가 일치하는 뉴스레터
            .orderBy(newsLetter.createdAt.desc()) // 생성일 기준 내림차순
            .limit(3) // 상위 3개만
            .fetch();
    }
}
