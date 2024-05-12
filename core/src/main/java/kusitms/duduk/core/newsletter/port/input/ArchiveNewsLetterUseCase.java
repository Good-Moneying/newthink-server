package kusitms.duduk.core.newsletter.port.input;

public interface ArchiveNewsLetterUseCase {

    // todo: 후에 응답 결과를 처리하는 DTO를 만들 예정
    void archiveNewsLetter(String email, Long newsLetterId);
}
