package kusitms.duduk.core.newsletter.port.input;

import kusitms.duduk.core.newsletter.dto.response.ArchiveNewsLetterResponse;

public interface ArchiveNewsLetterUseCase {

    ArchiveNewsLetterResponse archive(String email, Long newsLetterId);

    ArchiveNewsLetterResponse archiveWithCategory(String email, Long newsLetterId, String category);

}
