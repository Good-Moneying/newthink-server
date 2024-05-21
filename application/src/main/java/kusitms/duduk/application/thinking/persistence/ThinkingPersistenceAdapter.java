package kusitms.duduk.application.thinking.persistence;

import java.util.List;
import java.util.Optional;
import kusitms.duduk.application.thinking.persistence.entity.ThinkingJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.thinking.port.output.DeleteThinkingPort;
import kusitms.duduk.core.thinking.port.output.LoadThinkingPort;
import kusitms.duduk.core.thinking.port.output.SaveThinkingPort;
import kusitms.duduk.domain.thinking.Thinking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class ThinkingPersistenceAdapter implements SaveThinkingPort, LoadThinkingPort,
    DeleteThinkingPort {

    private final ThinkingRepository thinkingRepository;
    private final ThinkingJpaMapper thinkingJpaMapper;

    @Override
    public Optional<Thinking> findById(Long id) {
        return thinkingRepository.findById(id)
            .map(thinkingJpaMapper::toDomain);
    }

    @Override
    public List<Thinking> findAll() {
        return thinkingRepository.findAll()
            .stream()
            .map(thinkingJpaMapper::toDomain)
            .toList();
    }

    @Override
    public List<Thinking> findAllOrderByIsExistAscAndCreatedAtDesc(Long userId) {
        return thinkingRepository.findAllOrderByIsExistAscAndCreatedAtDesc(userId)
            .stream()
            .map(thinkingJpaMapper::toDomain)
            .toList();
    }

    @Override
    public Thinking save(Thinking thinking) {
        ThinkingJpaEntity thinkingJpaEntity = thinkingJpaMapper.toJpaEntity(thinking);
        ThinkingJpaEntity thinkingSaved = thinkingRepository.save(thinkingJpaEntity);
        return thinkingJpaMapper.toDomain(thinkingSaved);
    }

    @Override
    public void deleteAll() {
        thinkingRepository.deleteAll();
    }
}
