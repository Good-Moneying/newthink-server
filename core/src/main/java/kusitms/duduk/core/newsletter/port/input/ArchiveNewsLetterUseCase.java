package kusitms.duduk.core.newsletter.port.input;

import kusitms.duduk.core.newsletter.dto.response.ArchiveNewsLetterResponse;

public interface ArchiveNewsLetterUseCase {

    // todo: 후에 응답 결과를 처리하는 DTO를 만들 예정
    ArchiveNewsLetterResponse archive(String email, Long newsLetterId);
}
