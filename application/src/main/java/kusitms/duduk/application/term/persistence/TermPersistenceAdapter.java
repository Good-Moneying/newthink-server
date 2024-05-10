package kusitms.duduk.application.term.persistence;

import kusitms.duduk.application.term.persistence.entity.TermJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.term.port.output.SaveUserPort;
import kusitms.duduk.domain.term.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class TermPersistenceAdapter implements SaveUserPort {

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
}
