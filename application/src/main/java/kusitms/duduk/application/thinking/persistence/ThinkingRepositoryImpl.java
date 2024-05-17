package kusitms.duduk.application.thinking.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kusitms.duduk.application.thinking.persistence.entity.QThinkingJpaEntity;
import kusitms.duduk.application.thinking.persistence.entity.ThinkingJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class ThinkingRepositoryImpl implements ThinkingRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ThinkingJpaEntity> findAllOrderByIsExistAscAndCreatedAtAsc(Long userId) {
        QThinkingJpaEntity thinking = QThinkingJpaEntity.thinkingJpaEntity;

        return jpaQueryFactory.selectFrom(thinking)
            .orderBy(thinking.isCloudExist.asc(), thinking.createdAt.asc())
            .where(thinking.userId.eq(userId))
            .fetch();
    }
}