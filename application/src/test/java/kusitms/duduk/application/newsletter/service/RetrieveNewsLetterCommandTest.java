package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import kusitms.duduk.application.user.service.UserSteps;
import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.request.RetrieveNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
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
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DisplayName("RetrieveNewsLetterCommand 테스트")
@SpringBootTest
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
    private NewsLetter savedNewsLetter;

    @BeforeEach
    void setup() {
        User user = UserSteps.ROLE_USER_생성_요청();
        savedUser = saveUserPort.create(user);

        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(NewsLetterSteps.EDITOR_뉴스_레터_생성_요청());
        savedNewsLetter = saveNewsLetterPort.create(newsLetter);
    }

    @AfterEach
    void cleanUp() {
        deleteNewsLetterPort.deleteAll();
        deleteUserPort.deleteAll();
    }


    @Test
    void 뉴스레터_아이디로_뉴스레터를_조회한다() {
        // given
        RetrieveNewsLetterRequest request = new RetrieveNewsLetterRequest(
            savedNewsLetter.getNewsLetterId().getValue());

        // when
        NewsLetterResponse response = retrieveNewsLetterQuery.retrieveNewsLetterDetail(request);

        // then
        assertThat(response.title()).isEqualTo(savedNewsLetter.getTitle().getTitle());
        assertThat(response.content()).isEqualTo(savedNewsLetter.getContent().getContent());
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
        aiNewsLetters.forEach(request -> saveNewsLetterPort.create(newsLetterDtoMapper.toDomain(request)));

        // when
        List<NewsLetterThumbnailResponse> responses = retrieveNewsLetterQuery.retrieveRealtimeTrendNewsLetter(savedUser);

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
        aiNewsLetters.forEach(request -> saveNewsLetterPort.create(newsLetterDtoMapper.toDomain(request)));

        // when
        List<NewsLetterThumbnailResponse> responses = retrieveNewsLetterQuery.retrieveCustomizeNewsLetter(savedUser);

        // then
        assertThat(responses.size()).isEqualTo(3);
        assertThat(responses).allMatch(response -> {
            final String finance = "finance";
            return response.category().equalsIgnoreCase(finance);
        });
    }

}
