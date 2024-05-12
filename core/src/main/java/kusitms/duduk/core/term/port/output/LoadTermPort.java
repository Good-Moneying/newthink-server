package kusitms.duduk.core.term.port.output;

import java.util.Optional;
import kusitms.duduk.domain.term.Term;

public interface LoadTermPort {

    Optional<Term> findById(Long termId);

}
