package kusitms.duduk.core.term.port.output;

import kusitms.duduk.domain.term.Term;

public interface SaveUserPort {

    Term save(Term term);
    void saveAndFlush(Term term);
}
