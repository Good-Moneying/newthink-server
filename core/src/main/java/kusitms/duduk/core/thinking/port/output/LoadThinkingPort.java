package kusitms.duduk.core.thinking.port.output;

import java.util.List;
import java.util.Optional;
import kusitms.duduk.domain.thinking.Thinking;

public interface LoadThinkingPort {

    Optional<Thinking> findById(Long id);
    List<Thinking> findAll();

    List<Thinking> findAllOrderByIsExistAscAndCreatedAtAsc(Long userId);
}
