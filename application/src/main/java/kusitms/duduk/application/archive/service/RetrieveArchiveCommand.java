package kusitms.duduk.application.archive.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.common.exception.ErrorMessage;
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
        User user = loadUserByEmail(email);
        List<NewsLetter> newsLetters = getArchivedNewsLetters(user, category);

        return RetrieveArchivedNewsLetterResponse.from(newsLetters);
    }

    @Override
    public RetrieveArchivedTermResponse retrieveTerms(String email) {
        User user = loadUserByEmail(email);
        List<Term> terms = getArchivedTerms(user);

        return RetrieveArchivedTermResponse.from(terms);
    }

    private User loadUserByEmail(String email) {
        log.info("이메일로 사용자 조회: {}", email);
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("사용자를 찾을 수 없습니다."));
    }

    private List<NewsLetter> getArchivedNewsLetters(User user, Category category) {
        log.info("카테고리 '{}'에 대한 사용자의 아카이브된 뉴스레터를 조회합니다.", category);
        return user.getNewsLettersFromArchive(category)
            .stream()
            .map(this::loadNewsLetterById)
            .collect(Collectors.toList());
    }

    private List<Term> getArchivedTerms(User user) {
        log.info("사용자의 아카이브된 용어를 조회합니다.");
        return user.getTermsFromArchive()
            .stream()
            .map(this::loadTermById)
            .collect(Collectors.toList());
    }

    private NewsLetter loadNewsLetterById(Long id) {
        return loadNewsLetterPort.findById(id)
            .orElseThrow(() -> new NotExistsException(NEWS_LETTER_NOT_FOUND.getMessage()));
    }

    private Term loadTermById(Long id) {
        return loadTermPort.findById(id)
            .orElseThrow(() -> new NotExistsException(TERM_NOT_FOUND.getMessage()));
    }
}
