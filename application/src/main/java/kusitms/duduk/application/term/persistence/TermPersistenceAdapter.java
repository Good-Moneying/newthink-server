package kusitms.duduk.application.term.persistence;

import java.util.Optional;
import kusitms.duduk.application.term.persistence.entity.TermJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.term.port.output.DeleteTermPort;
import kusitms.duduk.core.term.port.output.LoadTermPort;
import kusitms.duduk.core.term.port.output.SaveTermPort;
import kusitms.duduk.domain.term.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class TermPersistenceAdapter implements SaveTermPort, LoadTermPort, DeleteTermPort {

    private final TermRepository termRepository;
    private final TermJpaMapper termJpaEntityMapper;

    @Override
    public Term save(Term term) {
        TermJpaEntity termJpaEntity = termJpaEntityMapper.toJpaEntity(term);
        TermJpaEntity savedTerm = termRepository.save(termJpaEntity);
        return termJpaEntityMapper.toDomain(savedTerm);
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

    @Override
    public Optional<Term> findLatestTerm() {
        return termRepository.findLatestTerm()
            .map(termJpaEntityMapper::toDomain);
    }

    @Override
    public void deleteById(Long termId) {
        termRepository.deleteById(termId);
    }


    @Override
    public void deleteAll() {
        termRepository.deleteAll();
    }
}
