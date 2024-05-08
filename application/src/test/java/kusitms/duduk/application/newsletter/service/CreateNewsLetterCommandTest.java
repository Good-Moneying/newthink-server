package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.core.user.port.output.SaveUserPort;
import kusitms.duduk.domain.user.User;
import kusitms.duduk.domain.user.vo.Acorn;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("CreateNewsLetterCommandTest 테스트")
public class CreateNewsLetterCommandTest {

    @Autowired
    private CreateNewsLetterUseCase createNewsLetterCommand;

    @Autowired
    private SaveUserPort saveUserPort;

    @Test
    void 뉴스_레터를_생성한다() {
        // given
        CreateNewsLetterRequest request = 뉴스_레터_생성_요청();
        String email = ROLE_EDITOR_생성_요청();

        // when
        NewsLetterResponse response = createNewsLetterCommand.create(request, email);

        // then
        assertThat(response).isNotNull();
        assertThat(response.title()).isEqualTo(request.title());
        assertThat(response.content()).isEqualTo(request.content());
    }

    @Test
    void 편집_권한이_없으면_뉴스_레터를_생성할_수_없다() {
        // given
        CreateNewsLetterRequest request = 뉴스_레터_생성_요청();
        String email = ROLE_USER_생성_요청();

        // when & then
        assertThatThrownBy(() -> createNewsLetterCommand.create(request, email))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private String ROLE_EDITOR_생성_요청() {
        String email = "test@test.com";

        User user = User.builder()
            .email(Email.of(email))
            .nickname(Nickname.of("tester"))
            .refreshToken(RefreshToken.of("12345"))
            .goal(Goal.EVERYDAY)
            .acorn(Acorn.initial())
            .gender(Gender.MALE)
            .birthday(LocalDate.of(1990, 1, 1))
            .provider(Provider.KAKAO)
            .role(Role.EDITOR)
            .build();

        saveUserPort.save(user);

        return email;
    }

    private String ROLE_USER_생성_요청() {
        String email = "test1@test.com";

        User user = User.builder()
            .email(Email.of(email))
            .nickname(Nickname.of("tester1"))
            .refreshToken(RefreshToken.of("123456"))
            .goal(Goal.EVERYDAY)
            .acorn(Acorn.initial())
            .gender(Gender.MALE)
            .birthday(LocalDate.of(1990, 1, 1))
            .provider(Provider.KAKAO)
            .role(Role.USER)
            .build();

        saveUserPort.save(user);

        return email;
    }

    private static CreateNewsLetterRequest 뉴스_레터_생성_요청() {
        String thumbnail = "https://image.com/tesla.jpg";
        String title = "테슬라 주가 갑자기 오르는 이유는?";
        String content = "불과 몇 주 사이에 미국 토화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까진 다들 '오해 6월에 금리 인하가 시작될 것'이라고 했다";
        String keywords = "테슬라, 전기차, 미국 연준";
        String category = "Stock";
        String summary = "물가 상승률이 예상보다 높다.\n급격한 기준 금리 인상한다\n이민자 증가한다\n";
        final String aiType = "AI";

        CreateNewsLetterRequest request = new CreateNewsLetterRequest(
            thumbnail,
            title,
            content,
            keywords,
            category,
            summary,
            aiType
        );
        return request;
    }
}
