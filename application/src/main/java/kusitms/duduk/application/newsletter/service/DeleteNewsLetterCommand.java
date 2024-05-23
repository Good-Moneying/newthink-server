package kusitms.duduk.application.newsletter.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.common.exception.custom.UnauthorizedException;
import kusitms.duduk.core.newsletter.port.input.DeleteNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class DeleteNewsLetterCommand implements DeleteNewsLetterUseCase {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final DeleteNewsLetterPort deleteNewsLetterPort;
    private final LoadUserPort loadUserPort;

    @Override
    public void delete(Long newsLetterId, String email) {
        NewsLetter newsLetter = loadNewsLetterById(newsLetterId);
        User user = loadUserByEmail(email);
        validateUserAuthorization(newsLetter, user);
        deleteNewsLetter(newsLetter.getId().getValue());
    }

    private NewsLetter loadNewsLetterById(Long newsLetterId) {
        return loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException(NEWS_LETTER_NOT_FOUND.getMessage()));
    }

    private User loadUserByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
    }

    private void validateUserAuthorization(NewsLetter newsLetter, User user) {
        if (!newsLetter.isWrittenBy(user.getId())) {
            throw new UnauthorizedException(UNAUTHORIZE_USER.getMessage());
        }
    }

    private void deleteNewsLetter(Long newsLetterId) {
        deleteNewsLetterPort.deleteById(newsLetterId);
    }

}
