package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.newsletter.port.input.ArchiveNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("ArchiveNewsLetterCommandTest 테스트")
public class ArchiveNewsLetterCommandTest {

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private LoadUserPort loadUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private SaveNewsLetterPort saveNewsLetterPort;

    @Autowired
    private ArchiveNewsLetterUseCase archiveNewsLetterUseCase;

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
    }
    @Test
    @Transactional
    void 뉴스레터를_아카이브한다() {
        // given
        User user = saveUserPort.create(UserSteps.ROLE_EDITOR_생성_요청());
        NewsLetter newsLetter = saveNewsLetterPort.create(NewsLetterSteps.AI_뉴스_레터_생성());

        String email = user.getEmail().getValue();
        Long newsLetterId = newsLetter.getNewsLetterId().getValue();

        // when
        archiveNewsLetterUseCase.archiveNewsLetter(email, newsLetterId);

        // then
        User loadUser = loadUserPort.findByEmail(email).get();
        Assertions.assertThat(loadUser.getArchives().get(0).getNewsLetterIds()).contains(newsLetterId);

        // todo : LazyInitializationException 발생
        /**
         * 트랜잭션 분리: archiveNewsLetter 메소드가 완료되고 해당 트랜잭션이 커밋된 후, loadUser 메소드를 호출할 때 새로운 트랜잭션에서 실행되는 경우입니다.
         * 이 때문에 이전 트랜잭션에서 사용되었던 Hibernate 세션이 이미 종료되어 있어, 지연 로딩을 위한 세션이 존재하지 않습니다.
         *
         * 지연 로딩 문제: User 엔티티의 archives 같은 지연 로딩 컬렉션에 접근하려고 할 때 세션이 이미 닫혀 있어서 Hibernate가 컬렉션을 초기화할 수 없습니다.
         * 이로 인해 LazyInitializationException이 발생합니다.
         */
    }
}
