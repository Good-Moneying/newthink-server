package kusitms.duduk.application.archive.service;

import java.util.List;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.archive.dto.response.ArchiveResponse;
import kusitms.duduk.core.archive.port.input.RetrieveArchiveUseCase;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RetrieveArchiveCommand implements RetrieveArchiveUseCase {

    private final LoadUserPort loadUserPort;

    @Override
    public ArchiveResponse retrieveNewsLetters(String email, Category category) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("User not found"));

        // todo : 아이디만 줄게 아니라 다르게 줘야함
        List<Long> newsLetterIds = user.getNewsLettersFromArchive(category);
        return new ArchiveResponse(newsLetterIds);
    }

    @Override
    public ArchiveResponse retrieveTerms(String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("User not found"));

        // todo : 아이디만 줄게 아니라 다르게 줘야함
        List<Long> termIds = user.getTermsFromArchive();
        return new ArchiveResponse(termIds);
    }
}
