package kusitms.duduk.application.comment.persistence;

import java.util.List;
import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {

    boolean existsByNewsLetterIdAndUserId(Long newsLetterId, Long userId);

    List<CommentJpaEntity> findByNewsLetterIdOrderByLikeCountDesc(Long newsLetterId);
}
