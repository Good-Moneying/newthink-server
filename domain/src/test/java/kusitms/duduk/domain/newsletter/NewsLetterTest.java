package kusitms.duduk.domain.newsletter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.newsletter.vo.Content;
import kusitms.duduk.domain.newsletter.vo.Keywords;
import kusitms.duduk.domain.newsletter.vo.Summary;
import kusitms.duduk.domain.newsletter.vo.Thumbnail;
import kusitms.duduk.domain.newsletter.vo.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("NewsLetter 테스트")
class NewsLetterTest {

    @Test
    void 뉴스_레터_객체를_생성한다() {
        // given
        String title = "title";
        String content = "content";
        String thumbnail = "thumbnail";
        String keywords = "keyword1,keyword2,keyword3";
        String category = "FINANCE";
        String summary = "summary\nsummary\nsummary";

        // when
        NewsLetter newsLetter = NewsLetter.builder()
            .title(Title.from(title))
            .content(Content.from(content))
            .thumbnail(Thumbnail.from(thumbnail))
            .keywords(Keywords.from(keywords))
            .category(Category.from(category))
            .summary(Summary.from(summary))
            .viewCount(Count.initial())
            .scrapCount(Count.from(6))
            .build();

        // then
        assertThat(newsLetter.getTitle().getTitle()).isEqualTo(title);
        assertThat(newsLetter.getContent().getContent()).isEqualTo(content);
        assertThat(newsLetter.getThumbnail().getUrl()).isEqualTo(thumbnail);
        assertThat(newsLetter.getKeywords().getWords().size()).isEqualTo(3);
        assertThat(newsLetter.getCategory().name()).isEqualTo(category);
        assertThat(newsLetter.getSummary().getSentences().size()).isEqualTo(3);
        assertThat(newsLetter.getViewCount().getCount()).isEqualTo(0);
        assertThat(newsLetter.getScrapCount().getCount()).isEqualTo(6);
    }
}