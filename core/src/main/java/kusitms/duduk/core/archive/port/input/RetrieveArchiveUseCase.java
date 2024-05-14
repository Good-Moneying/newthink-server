package kusitms.duduk.core.archive.port.input;

import kusitms.duduk.core.archive.dto.response.RetrieveArchivedNewsLetterResponse;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedTermResponse;
import kusitms.duduk.domain.global.Category;

public interface RetrieveArchiveUseCase {

    RetrieveArchivedNewsLetterResponse retrieveNewsLetters(String email, Category category);

    RetrieveArchivedTermResponse retrieveTerms(String email);
}
