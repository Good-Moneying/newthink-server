package kusitms.duduk.core.term.port.input;

import kusitms.duduk.core.term.dto.response.ArchiveTermResponse;

public interface ArchiveTermUseCase {
    ArchiveTermResponse archive(String email, Long termId);
}
