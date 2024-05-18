package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterDetailResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.core.newsletter.port.input.RetrieveNewsLetterQuery;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.user.port.output.DeleteUserPort;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DisplayName("RetrieveNewsLetterCommand 테스트")
@SpringBootTest
@ActiveProfiles("test")
public class RetrieveNewsLetterCommandTest {

    @Autowired
    private SaveNewsLetterPort saveNewsLetterPort;

    @Autowired
    private NewsLetterDtoMapper newsLetterDtoMapper;

    @Autowired
    private DeleteNewsLetterPort deleteNewsLetterPort;

    @Autowired
    private SaveUserPort saveUserPort;

    @Autowired
    private DeleteUserPort deleteUserPort;

    @Autowired
    private RetrieveNewsLetterQuery retrieveNewsLetterQuery;

    private User savedUser;
    private User savedEditor;
    private NewsLetter savedNewsLetter;

    private NewsLetter aiNewsLetter;

    @BeforeEach
    void setup() {
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);

        User editor = UserSteps.ROLE_EDITOR_생성_요청();
        savedEditor = saveUserPort.create(editor);

        NewsLetter newsLetter = NewsLetterSteps.EDITOR_뉴스_레터_생성(savedEditor.getId().getValue());
        savedNewsLetter = saveNewsLetterPort.create(newsLetter);

        NewsLetter ai = NewsLetterSteps.AI_뉴스_레터_생성();
        aiNewsLetter = saveNewsLetterPort.create(ai);
    }

    @AfterEach
    void cleanUp() {
        deleteNewsLetterPort.deleteAll();
        deleteUserPort.deleteAll();
    }


    @Test
    void 유저_아이디와_뉴스레터_아이디로_에디터_뉴스레터를_조회한다() {
        // when
        NewsLetterDetailResponse response = retrieveNewsLetterQuery.retrieveNewsLetterDetail(
            savedEditor.getEmail().getValue(),
            savedNewsLetter.getId().getValue());

        // todo : NewsLetter Comment가 잘 불러오는지 확인해야됨

        // then
        assertThat(response).isNotNull();
        assertThat(response.title()).isEqualTo(savedNewsLetter.getTitle().getTitle());
        assertThat(response.editor().getNickname()).isEqualTo(savedEditor.getNickname().getValue());
    }

    @Test
    void 뉴스레터_아이디로_AI_뉴스레터를_조회한다() {
        // when
        NewsLetterDetailResponse response = retrieveNewsLetterQuery.retrieveNewsLetterDetail(
            savedUser.getEmail().getValue(),
            aiNewsLetter.getId().getValue());

        // then
        assertThat(response).isNotNull();
        assertThat(response.title()).isEqualTo(savedNewsLetter.getTitle().getTitle());
        // body를 Null로 놔두는 것이 맞는가
        assertThat(response.body()).isNull();
    }

    @Test
    void 가장_최근의_뉴스레터를_조회한다() {
        // when & then
        NewsLetterThumbnailResponse response = retrieveNewsLetterQuery.retrieveLatestNewsLetter(
            savedUser);

        // then
        assertThat(response.title()).isEqualTo(savedNewsLetter.getTitle().getTitle());
        assertThat(response.isScrapped()).isEqualTo(false);
    }

    @Test
    void AI_뉴스레터만_조회한다() {
        // given
        // 테스트에 필요한 AI 뉴스레터 목록을 생성하고 저장
        List<CreateNewsLetterRequest> aiNewsLetters = List.of(
            NewsLetterSteps.AI_뉴스_레터_생성_요청(),
            NewsLetterSteps.AI_뉴스_레터_생성_요청(),
            NewsLetterSteps.AI_뉴스_레터_생성_요청(),
            NewsLetterSteps.EDITOR_뉴스_레터_생성_요청(),
            NewsLetterSteps.EDITOR_뉴스_레터_생성_요청(),
            NewsLetterSteps.EDITOR_뉴스_레터_생성_요청()
        );
        aiNewsLetters.forEach(
            request -> saveNewsLetterPort.create(newsLetterDtoMapper.toDomain(request)));

        // when
        List<NewsLetterThumbnailResponse> responses = retrieveNewsLetterQuery.retrieveRealtimeTrendNewsLetter(
            savedUser);

        // then
        assertThat(responses.size()).isEqualTo(3);
        assertThat(responses).allMatch(response -> response.type().equals("AI"));
    }

    @Test
    void 사용자_관심사에_맞는_뉴스레터를_조회한다() {
        // given
        List<CreateNewsLetterRequest> aiNewsLetters = List.of(
            NewsLetterSteps.AI_뉴스_레터_생성_요청(),
            NewsLetterSteps.AI_뉴스_레터_생성_요청(),
            NewsLetterSteps.AI_뉴스_레터_생성_요청(),
            NewsLetterSteps.EDITOR_뉴스_레터_생성_요청(),
            NewsLetterSteps.EDITOR_뉴스_레터_생성_요청(),
            NewsLetterSteps.EDITOR_뉴스_레터_생성_요청()
        );
        aiNewsLetters.forEach(
            request -> saveNewsLetterPort.create(newsLetterDtoMapper.toDomain(request)));

        // when
        List<NewsLetterThumbnailResponse> responses = retrieveNewsLetterQuery.retrieveCustomizeNewsLetter(
            savedUser);

        // then
        assertThat(responses.size()).isEqualTo(3);
        assertThat(responses).allMatch(response -> {
            final String finance = "finance";
            return response.category().equalsIgnoreCase(finance);
        });
    }

}
