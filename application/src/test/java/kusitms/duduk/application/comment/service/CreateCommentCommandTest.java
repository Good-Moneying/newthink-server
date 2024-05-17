package kusitms.duduk.application.comment.service;

import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.application.newsletter.service.NewsLetterSteps;
import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import kusitms.duduk.core.comment.port.input.CreateCommentUseCase;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CreateCommentCommandTest {

    @Autowired
    private CreateCommentUseCase createCommentUseCase;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private SaveNewsLetterPort saveNewsLetterPort;

    @Autowired
    private DeleteNewsLetterPort deleteNewsLetterPort;

    @Autowired
    private LoadUserPort loadUserPort;

    @Autowired
    private LoadNewsLetterPort loadNewsLetterPort;

    private User savedUser;
    private NewsLetter savedNewsLetter;

    @BeforeEach
    void setup() {
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);

        NewsLetter newsLetter = NewsLetterSteps.AI_FINANCE_뉴스_레터_생성();
        savedNewsLetter = saveNewsLetterPort.create(newsLetter);
    }

    @AfterEach
    void tearDown() {
        deleteUserPort.deleteById(savedUser.getId().getValue());
        deleteNewsLetterPort.deleteById(savedNewsLetter.getId().getValue());
    }

    @Test
    @Transactional
    void 코멘트를_생성한다() {
        // given
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("코멘트 내용")
            .perspective("POSITIVE")
            .isPrivate(false)
            .build();

        String email = savedUser.getEmail().getValue();
        Long newsLetterId = savedNewsLetter.getId().getValue();

        // when
        CommentResponse response = createCommentUseCase.create(email, newsLetterId, request);

        // then

        User loadedUser = loadUserPort.findByEmail(email).get();
        NewsLetter loadedNewsLetter = loadNewsLetterPort.findById(newsLetterId).get();

        assertThat(response.newsLetterId())
            .isEqualTo(savedNewsLetter.getId().getValue());
        assertThat(response.content()).isEqualTo("코멘트 내용");
        assertThat(response.perspective()).isEqualTo("POSITIVE");
        assertThat(loadedUser.getComments().get(0).getSentence().getValue()).isEqualTo("코멘트 내용");
        assertThat(loadedNewsLetter.getComments().get(0).getSentence().getValue()).isEqualTo(
            "코멘트 내용");
    }

    @Test
    void 코멘트_생성_후_요약_문장이_생성된다() {

    }
}