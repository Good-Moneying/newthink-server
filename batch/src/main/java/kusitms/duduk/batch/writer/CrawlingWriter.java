package kusitms.duduk.batch.writer;

import kusitms.duduk.application.newsletter.persistence.NewsLetterPersistenceAdapter;
import kusitms.duduk.application.newsletter.service.CreateNewsLetterCommand;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CrawlingWriter implements ItemWriter<NewsLetter> {

    private final NewsLetterPersistenceAdapter newsLetterPersistenceAdapter;

    @Override
    public void write(Chunk<? extends NewsLetter> chunk) throws Exception {
        for (NewsLetter newsLetter : chunk.getItems()) {
            newsLetterPersistenceAdapter.save(newsLetter);
        }
    }
}
