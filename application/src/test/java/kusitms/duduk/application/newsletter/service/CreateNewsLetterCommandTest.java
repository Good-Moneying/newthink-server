package kusitms.duduk.application.newsletter.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import kusitms.duduk.core.annotation.Mapper;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.newsletter.vo.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import kusitms.duduk.domain.newsletter.vo.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@DisplayName("CreateNewsLetterCommandTest 테스트")
public class CreateNewsLetterCommandTest {

    @Autowired
    private NewsLetterDtoMapper newsLetterDtoMapper;

    @Test
    void AI_뉴스레터를_생성한다() {
        // given
        String thumbnail = "https://image.com/tesla.jpg";
        String title = "테슬라 주가 갑자기 오르는 이유는?";
        String content = "불과 몇 주 사이에 미국 토화 정책을 바라보는 분위기가 완전히 바뀌었어요. 얼마 전까진 다들 '오해 6월에 금리 인하가 시작될 것'이라고 했다";
        String keywords = "테슬라, 전기차, 미국 연준";
        String category = "Stock";
        String summary = "물가 상승률이 예상보다 높다.\n급격한 기준 금리 인상한다\n이민자 증가한다\n";
        final String aiType = "AI";

        CreateAiNewsLetterRequest request = new CreateAiNewsLetterRequest(
            thumbnail,
            title,
            content,
            keywords,
            category,
            summary,
            aiType
        );

        CreateNewsLetterUseCase createNewsLetterCommand = new CreateNewsLetterCommand();

        // when
        NewsLetter newsLetter = createNewsLetterCommand.create(request);

        // then
        assertThat(newsLetter).isNotNull();
        assertThat(newsLetter.getSummary().getSentences().size()).isEqualTo(3);
        assertThat(newsLetter.getSummary().getSentences().get(0)).isEqualTo("물가 상승률이 예상보다 높다.");
        assertThat(newsLetter.getSummary().getSentences().get(1)).isEqualTo("급격한 기준 금리 인상한다");
    }

    private record CreateAiNewsLetterRequest(String thumbnail, String title, String content,
		             String keywords, String category, String summary,
		             String aiType) {

        private CreateAiNewsLetterRequest {
            Assert.notNull(title, "title must not be null");
            Assert.notNull(content, "content must not be null");
            Assert.notNull(keywords, "keywords must not be null");
            Assert.notNull(category, "category must not be null");
            Assert.notNull(summary, "summary must not be null");
            Assert.notNull(aiType, "aiType must not be null");
        }
    }

    interface CreateNewsLetterUseCase {

        NewsLetter create(CreateAiNewsLetterRequest request);
    }

    class CreateNewsLetterCommand implements CreateNewsLetterUseCase {

        @Override
        public NewsLetter create(CreateAiNewsLetterRequest request) {
            return newsLetterDtoMapper.toDomain(request);
        }
    }

    @Mapper
    class NewsLetterDtoMapper {

        public NewsLetter toDomain(CreateAiNewsLetterRequest request) {
            return NewsLetter.builder()
	.thumbnail(Thumbnail.from(request.thumbnail()))
	.title(Title.from(request.title()))
	.content(Content.from(request.content()))
	.keywords(Keywords.from(request.keywords()))
	.category(Category.from(request.category()))
	.summary(Summary.from(request.summary()))
	.type(Type.valueOf(request.aiType()))
	.build();
        }
    }
}
