package kusitms.duduk.application.newsletter.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
public class NewsLetterEventListenerTest {

    @MockBean
    private NewsLetterEventListener newsLetterEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    public void setup() {
        // Ensure correct initialization
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void 조회_이벤트가_빨생하면_리스너가_호출된다() {
        // given
        RetrieveNewsLetterEvent event = new RetrieveNewsLetterEvent(this, 1L);

        // when
        applicationEventPublisher.publishEvent(event);

        // then
        Mockito.verify(newsLetterEventListener).increaseViewCount(event);
    }

    @Test
    void 구독_이벤트가_발생하면_리스너가_호출된다() {
        // given
        ArchiveNewsLetterEvent event = new ArchiveNewsLetterEvent(this, 1L);

        // when
        applicationEventPublisher.publishEvent(event);

        // then
        Mockito.verify(newsLetterEventListener).increaseScrapCount(event);
    }
}
