package kusitms.duduk.application.thinking.persistence;

import java.util.List;
import kusitms.duduk.application.thinking.persistence.entity.ThinkingJpaEntity;

public interface ThinkingRepositoryCustom {
    List<ThinkingJpaEntity> findAllOrderByIsExistAscAndCreatedAtAsc(Long userId);
}
