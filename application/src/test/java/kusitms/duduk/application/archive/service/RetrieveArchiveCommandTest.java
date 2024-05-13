package kusitms.duduk.application.archive.service;

import kusitms.duduk.application.newsletter.service.NewsLetterSteps;
import kusitms.duduk.application.term.service.TermSteps;
import kusitms.duduk.core.archive.port.input.RetrieveArchiveUseCase;
import kusitms.duduk.core.newsletter.port.input.ArchiveNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.term.dto.response.ArchiveTermResponse;
import kusitms.duduk.core.term.port.input.ArchiveTermUseCase;
import kusitms.duduk.core.term.port.output.DeleteTermPort;
import kusitms.duduk.core.term.port.output.SaveTermPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.term.Term;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.archive.dto.response.ArchiveResponse;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("RetrieveArchiveCommand 테스트")
public class RetrieveArchiveCommandTest {

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private SaveNewsLetterPort saveNewsLetterPort;

    @Autowired
    private DeleteNewsLetterPort deleteNewsLetterPort;

    @Autowired
    private SaveTermPort saveTermPort;

    @Autowired
    private DeleteTermPort deleteTermPort;

    @Autowired
    private RetrieveArchiveUseCase retrieveArchiveUseCase;

    @Autowired
    private ArchiveNewsLetterUseCase archiveNewsLetterUseCase;

    @Autowired
    private ArchiveTermUseCase archiveTermUseCase;

    private User savedUser;

    @BeforeEach
    void setup() {
        // 사용자와 뉴스레터, 약관 데이터 생성 및 저장
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);
    }

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
        deleteNewsLetterPort.deleteAll();
        deleteTermPort.deleteAll();
    }

    @Test
    @Transactional
    void 뉴스레터_카테고리별_아카이브를_조회한다() {
        // given
        // 뉴스레터 생성
        NewsLetter newsLetter = NewsLetterSteps.AI_FINANCE_뉴스_레터_생성();
        NewsLetter savedNewsLetter = saveNewsLetterPort.create(newsLetter);

        String email = savedUser.getEmail().getValue();
        Long newsLetterId = savedNewsLetter.getNewsLetterId().getValue();

        archiveNewsLetterUseCase.archive(savedUser.getEmail().getValue(),
            savedNewsLetter.getNewsLetterId().getValue());

        // when
        ArchiveResponse newsLettersResponse = retrieveArchiveUseCase.retrieveNewsLetters(email,
            Category.FINANCE);

        // then
        Assertions.assertThat(newsLettersResponse.ids()).contains(newsLetterId);
    }

    @Test
    @Transactional
    void 단어_아카이브를_조회한다() {
        // given
        // 단어 생성
        Term term = TermSteps.COMPANY_단어_생성();
        Term savedTerm = saveTermPort.save(term);

        String email = savedUser.getEmail().getValue();
        Long termId = savedTerm.getId().getValue();

        archiveTermUseCase.archive(savedUser.getEmail().getValue(),
            savedTerm.getId().getValue());

        // when
        ArchiveResponse response = retrieveArchiveUseCase.retrieveTerms(email);

        // then
        Assertions.assertThat(response.ids()).contains(termId);
    }
}
