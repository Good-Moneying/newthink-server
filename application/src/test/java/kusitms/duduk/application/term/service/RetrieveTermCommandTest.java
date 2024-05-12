package kusitms.duduk.application.term.service;

import static org.junit.jupiter.api.Assertions.*;

import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.term.dto.TermDtoMapper;
import kusitms.duduk.core.term.dto.request.CreateTermRequest;
import kusitms.duduk.core.term.dto.request.RetrieveTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.core.term.port.input.RetrieveTermQuery;
import kusitms.duduk.core.term.port.output.SaveTermPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("RetrieveTermCommand 테스트")
class RetrieveTermCommandTest {

    @Autowired
    private SaveTermPort saveTermPort;

    @Autowired
    private RetrieveTermQuery retrieveTermQuery;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    DeleteUserPort deleteUserPort;

    private User savedUser;

    @BeforeEach
    void setup() {
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);
    }

    @AfterEach
    void cleanUp() {
        // todo : Term deleteAll() 해야될까?
        deleteUserPort.deleteAll();
    }

    @Test
    void 단어를_조회한다() {
        // given
        Term term = TermSteps.단어_생성();
        Term savedTerm = saveTermPort.save(term);

        // when
        RetrieveTermResponse response = retrieveTermQuery.retrieve(
            new RetrieveTermRequest(savedTerm.getId().getValue()));

        // then
        assertEquals(savedTerm.getId().getValue(), response.termId());
        assertEquals(savedTerm.getKoreanName().getValue(), response.koreanName());
        assertEquals(savedTerm.getEnglishName().getValue(), response.englishName());
        assertEquals(savedTerm.getDescription().getValue(), response.description());
        assertEquals(savedTerm.getTermCategory().name(), response.category());
    }

    @Test
    void 여러_개의_단어가_있을_경우_가장_최근의_단어를_조회한다() {
        // given
        Term term1 = TermSteps.단어_생성();
        Term term2 = TermSteps.단어_생성_2();

        Term savedTerm1 = saveTermPort.save(term1);
        Term savedTerm2 = saveTermPort.save(term2);
        // when
        RetrieveTermResponse response = retrieveTermQuery.retrieveLatestTerm(savedUser);

        // then
        assertEquals(savedTerm2.getId().getValue(), response.termId());
        assertEquals(savedTerm2.getKoreanName().getValue(), response.koreanName());
        assertEquals(savedTerm2.getEnglishName().getValue(), response.englishName());
        assertEquals(savedTerm2.getDescription().getValue(), response.description());
        assertEquals(savedTerm2.getTermCategory().name(), response.category());
        assertEquals(false, response.isScrapped());
    }
}