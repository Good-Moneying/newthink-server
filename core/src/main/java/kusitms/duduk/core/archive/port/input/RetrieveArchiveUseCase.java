package kusitms.duduk.core.archive.port.input;

import kusitms.duduk.core.archive.dto.response.ArchiveResponse;
import kusitms.duduk.domain.global.Category;

public interface RetrieveArchiveUseCase {

    ArchiveResponse retrieveNewsLetters(String email, Category category);

    ArchiveResponse retrieveTerms(String email);
}
