package kusitms.duduk.application.comment.persistence;

import kusitms.duduk.application.comment.persistence.entity.CommentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {

}
