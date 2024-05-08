package kusitms.duduk.core.newsletter.port.input;

public interface DeleteNewsLetterUseCase {

    void delete(Long newsLetterId, String email);
}
