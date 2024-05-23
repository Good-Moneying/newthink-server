package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.common.exception.custom.UnauthorizedException;
import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.port.input.DeleteNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DeleteNewsLetterCommandTest {

    @Autowired
    private DeleteNewsLetterUseCase deleteNewsLetterUseCase;

    @Autowired
    private SaveNewsLetterPort saveNewsLetterPort;

    @Autowired
    private LoadNewsLetterPort loadNewsLetterPort;

    @Autowired
    private DeleteNewsLetterPort deleteNewsLetterPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private NewsLetterDtoMapper newsLetterDtoMapper;

    @AfterEach
    void cleanUp() {
        deleteUserPort.deleteAll();
        deleteNewsLetterPort.deleteAll();
    }

    @Test
    void 뉴스레터를_삭제한다() {
        // given
        User user = UserSteps.ROLE_EDITOR_생성_요청();
        User savedUser = saveUserPort.create(user);

        CreateNewsLetterRequest request = NewsLetterSteps.AI_뉴스_레터_생성_요청();
        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(request, savedUser.getId());
        NewsLetter savedNewsLetter = saveNewsLetterPort.create(newsLetter);

        // when
        deleteNewsLetterUseCase.delete(savedNewsLetter.getId().getValue(),
            user.getEmail().getValue());

        // then
        Assertions.assertThat(loadNewsLetterPort.findAll().size()).isEqualTo(0L);
    }

    @Test
    void 작성자가_아니면_뉴스레터를_삭제할_수_없다() {
        // given
        User user = UserSteps.ROLE_EDITOR_생성_요청();
        saveUserPort.create(user);

        CreateNewsLetterRequest request = NewsLetterSteps.AI_뉴스_레터_생성_요청();
        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(request, Id.of(99L));
        NewsLetter savedNewsLetter = saveNewsLetterPort.create(newsLetter);

        // when & then
        assertThatThrownBy(
            () -> deleteNewsLetterUseCase.delete(savedNewsLetter.getId().getValue(),
	user.getEmail().getValue())).isInstanceOf(UnauthorizedException.class);
    }
}