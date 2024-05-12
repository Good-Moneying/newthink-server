package kusitms.duduk.application.term.persistence;

import java.util.Optional;
import kusitms.duduk.application.term.persistence.entity.TermJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.term.port.output.LoadTermPort;
import kusitms.duduk.core.term.port.output.SaveTermPort;
import kusitms.duduk.domain.term.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class TermPersistenceAdapter implements SaveTermPort, LoadTermPort {

    private final TermRepository termRepository;
    private final TermJpaMapper termJpaEntityMapper;

    @Override
    public Term save(Term term) {
        TermJpaEntity termJpaEntity = termJpaEntityMapper.toJpaEntity(term);
        TermJpaEntity termSaved = termRepository.save(termJpaEntity);
        log.info("termSaved: {}\n", termSaved.toString());
        return termJpaEntityMapper.toDomain(termSaved);
    }

    @Override
    public void saveAndFlush(Term term) {
        TermJpaEntity termJpaEntity = termJpaEntityMapper.toJpaEntity(term);
        termRepository.saveAndFlush(termJpaEntity);
    }

    @Override
    public Optional<Term> findById(Long termId) {
        return termRepository.findById(termId)
            .map(termJpaEntityMapper::toDomain);
    }
}
