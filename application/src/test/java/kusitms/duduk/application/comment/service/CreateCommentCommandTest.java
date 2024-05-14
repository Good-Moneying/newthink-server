package kusitms.duduk.application.comment.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import kusitms.duduk.application.newsletter.service.NewsLetterSteps;
import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.comment.dto.request.CreateCommentRequest;
import kusitms.duduk.core.comment.dto.response.CommentResponse;
import kusitms.duduk.core.comment.port.input.CreateCommentUseCase;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        deleteNewsLetterPort.deleteById(savedNewsLetter.getNewsLetterId().getValue());
    }

    @Test
    void 코멘트를_생성한다() {
        // given
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("코멘트 내용")
            .perspective("POSITIVE")
            .build();

        String email = savedUser.getEmail().getValue();
        Long newsLetterId = savedNewsLetter.getNewsLetterId().getValue();

        // when
        CommentResponse response = createCommentUseCase.create(email, newsLetterId, request);

        // then
        assertThat(response.email()).isEqualTo(savedUser.getEmail().getValue());
        assertThat(response.newsLetterId())
            .isEqualTo(savedNewsLetter.getNewsLetterId().getValue());
        assertThat(response.content()).isEqualTo("코멘트 내용");
        assertThat(response.perspective()).isEqualTo("POSITIVE");
    }

    @Test
    void 코멘트_생성_후_요약_문장이_생성된다() {

    }
}