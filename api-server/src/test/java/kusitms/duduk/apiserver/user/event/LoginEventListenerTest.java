package kusitms.duduk.apiserver.user.event;

import kusitms.duduk.application.user.event.LoginUserEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
public class LoginEventListenerTest {

    @MockBean
    private LoginEventListener loginEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    public void setup() {
        // Ensure correct initialization
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void 로그인_이벤트가_발생하면_리스너가_호출된다() {
        // given
        // Event 인스턴스를 생성한다
        LoginUserEvent event = new LoginUserEvent(this, "test@test,com");

        // when
        // 이벤트를 발행한다
        applicationEventPublisher.publishEvent(event);

        // then
        // verify 메소드를 활요하여 리스너의 attendUser 메소드가 호출되었는지 확인한다
        Mockito.verify(loginEventListener).attendUser(event);
    }
}