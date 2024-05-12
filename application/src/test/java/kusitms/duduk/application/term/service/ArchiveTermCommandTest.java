package kusitms.duduk.application.term.service;

import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.term.port.input.ArchiveTermUseCase;
import kusitms.duduk.core.term.port.output.SaveTermPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("ArchiveTermCommandTest 테스트")
public class ArchiveTermCommandTest {

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private LoadUserPort loadUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private SaveTermPort saveTermPort;

    @Autowired
    private ArchiveTermUseCase archiveTermUseCase;

    private final int WORD_ARCHIVE_INDEX = 4;

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
    }

    @Test
    @Transactional
    void 단어를_아카이브한다() {
        // given
        User user = saveUserPort.create(UserSteps.ROLE_EDITOR_생성_요청());
        Term term = saveTermPort.save(TermSteps.단어_생성());

        String email = user.getEmail().getValue();
        Long termId = term.getId().getValue();

        // when
        archiveTermUseCase.archive(email, termId);

        // then
        User loadUser = loadUserPort.findByEmail(email).get();
        Assertions.assertThat(loadUser.getArchives().get(WORD_ARCHIVE_INDEX).getTermIds()).contains(termId);
    }
}
