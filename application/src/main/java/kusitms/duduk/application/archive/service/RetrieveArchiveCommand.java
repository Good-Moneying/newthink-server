package kusitms.duduk.application.archive.service;

import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedNewsLetterResponse;
import kusitms.duduk.core.archive.dto.response.RetrieveArchivedTermResponse;
import kusitms.duduk.core.archive.port.input.RetrieveArchiveUseCase;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.term.port.output.LoadTermPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class RetrieveArchiveCommand implements RetrieveArchiveUseCase {

    private final LoadUserPort loadUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;
    private final LoadTermPort loadTermPort;

    @Override
    public RetrieveArchivedNewsLetterResponse retrieveNewsLetters(String email, Category category) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("User not found"));

        List<NewsLetter> newsLetters = user.getNewsLettersFromArchive(category)
            .stream()
            .map(id -> loadNewsLetterPort.findById(id)
	.orElseThrow(() -> new NotExistsException("NewsLetter not found")))
            .collect(Collectors.toList());

        return RetrieveArchivedNewsLetterResponse.from(newsLetters);
    }

    @Override
    public RetrieveArchivedTermResponse retrieveTerms(String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("User not found"));

        List<Term> terms = user.getTermsFromArchive()
            .stream()
            .map(id -> loadTermPort.findById(id)
	.orElseThrow(() -> new NotExistsException("Term not found")))
            .collect(Collectors.toList());

        return RetrieveArchivedTermResponse.from(terms);
    }
}
