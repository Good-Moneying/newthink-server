package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.application.newsletter.event.ArchiveNewsLetterEvent;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.newsletter.dto.response.ArchiveNewsLetterResponse;
import kusitms.duduk.core.newsletter.port.input.ArchiveNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ArchiveNewsLetterCommand implements ArchiveNewsLetterUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public ArchiveNewsLetterResponse archive(String email, Long newsLetterId) {

        User user = loadUserByEmail(email);
        NewsLetter newsLetter = loadNewsLetterById(newsLetterId);

        publishEvent(newsLetterId);

        archiveNewsLetterForUser(user, newsLetter);
        updateUser(user);
        log.info("뉴스레터를 아카이브 합니다 archive at : {} newsLetterId: {}", newsLetter.getCategory().getDescription(), newsLetterId);

        return new ArchiveNewsLetterResponse(newsLetterId);
    }

    private User loadUserByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("User not found"));
    }

    private NewsLetter loadNewsLetterById(Long newsLetterId) {
        return loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException("NewsLetter not found"));
    }

    private void publishEvent(Long newsLetterId) {
        applicationEventPublisher.publishEvent(new ArchiveNewsLetterEvent(this, newsLetterId));
    }

    private void archiveNewsLetterForUser(User user, NewsLetter newsLetter) {
        user.archiveNewsLetter(newsLetter);
    }

    private void updateUser(User user) {
        updateUserPort.update(user);
    }
}
