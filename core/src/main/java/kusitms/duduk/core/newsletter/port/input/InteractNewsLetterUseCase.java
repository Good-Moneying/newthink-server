package kusitms.duduk.core.newsletter.port.input;

public interface InteractNewsLetterUseCase {
    void increaseViewCount(Long id);

    void increaseScrapCount(Long id);
}
