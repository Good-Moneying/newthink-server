package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.port.input.DeleteNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteNewsLetterCommand implements DeleteNewsLetterUseCase {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final DeleteNewsLetterPort deleteNewsLetterPort;
    private final LoadUserPort loadUserPort;

    @Override
    public void delete(Long newsLetterId, String email) {
        log.info("delete newsLetterId: {}", newsLetterId);
        NewsLetter newsLetter = loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new IllegalArgumentException("NewsLetter not found"));

        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (newsLetter.isWrittenBy(user.getId())) {
            deleteNewsLetterPort.deleteById(newsLetter.getNewsLetterId().getValue());
        } else {
            throw new IllegalArgumentException("User is not the author of the newsLetter");
        }
    }
}