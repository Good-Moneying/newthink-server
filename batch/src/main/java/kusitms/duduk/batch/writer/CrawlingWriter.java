package kusitms.duduk.batch.writer;

import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CrawlingWriter implements ItemWriter<CreateNewsLetterRequest> {

    private final CreateNewsLetterUseCase createNewsLetterUseCase;

    @Override
    public void write(Chunk<? extends CreateNewsLetterRequest> chunk) throws Exception {
        for (CreateNewsLetterRequest createNewsLetterRequest : chunk.getItems()) {
            createNewsLetterUseCase.create(createNewsLetterRequest);
        }
    }
}