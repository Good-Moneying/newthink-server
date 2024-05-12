package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.newsletter.port.input.ArchiveNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ArchiveNewsLetterCommand implements ArchiveNewsLetterUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;

    @Override
    public void archiveNewsLetter(String email, Long newsLetterId) {
        // 유저를 찾는다
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("User not found"));

        // 뉴스레터를 찾는다
        NewsLetter newsLetter = loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException("NewsLetter not found"));

        // 유저의 아카이브에 추가한다
        user.archiveNewsLetter(newsLetter);
        updateUserPort.update(user);
    }
}
