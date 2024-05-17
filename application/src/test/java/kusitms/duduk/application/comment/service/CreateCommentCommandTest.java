package kusitms.duduk.application.comment.service;

import static kusitms.duduk.application.comment.service.CommentSteps.*;
import static org.assertj.core.api.Assertions.*;

import kusitms.duduk.application.newsletter.service.NewsLetterSteps;
import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import kusitms.duduk.core.comment.port.input.CreateCommentUseCase;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.thinking.port.output.DeleteThinkingPort;
import kusitms.duduk.core.thinking.port.output.LoadThinkingPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.thinking.Thinking;
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

    @Autowired
    private LoadThinkingPort loadThinkingPort;

    @Autowired
    private DeleteThinkingPort deleteThinkingPort;

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
        deleteThinkingPort.deleteAll();
    }

    @Test
    @Transactional
    void 코멘트를_생성한다() {
        // given
        CreateCommentRequest request = 코멘트_생성_요청();

        String email = savedUser.getEmail().getValue();
        Long newsLetterId = savedNewsLetter.getId().getValue();

        // when
        CommentResponse response = createCommentUseCase.create(email, newsLetterId, request);

        // then

        User loadedUser = loadUserPort.findByEmail(email).get();
        NewsLetter loadedNewsLetter = loadNewsLetterPort.findById(newsLetterId).get();

        assertThat(response.newsLetterId())
            .isEqualTo(savedNewsLetter.getId().getValue());
        assertThat(response.content()).isEqualTo(request.content());
        assertThat(response.perspective()).isEqualToIgnoringCase(request.perspective());
        assertThat(loadedUser.getComments().get(0).getSentence().getValue()).isEqualTo(
            request.content());
        assertThat(loadedNewsLetter.getComments().get(0).getSentence().getValue()).isEqualTo(
            request.content());
    }

    @Test
    void 코멘트_생성_후_생각_엔티티가_생성된다() {
        // given
        CreateCommentRequest request = 코멘트_생성_요청();

        String email = savedUser.getEmail().getValue();
        Long newsLetterId = savedNewsLetter.getId().getValue();

        // when
        createCommentUseCase.create(email, newsLetterId, request);

        // then
        assertThat(loadThinkingPort.findAll().size()).isGreaterThan(0);
    }
}