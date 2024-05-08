package kusitms.duduk.application.newsletter.persistence;

import java.util.Optional;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.core.annotation.Adapter;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class NewsLetterPersistenceAdapter implements LoadNewsLetterPort, DeleteNewsLetterPort,
    SaveNewsLetterPort, UpdateNewsLetterPort {

    private final NewsLetterRepository newsLetterRepository;
    private final NewsLetterJpaMapper newsLetterJpaMapper;

    @Override
    public NewsLetter save(NewsLetter newsLetter) {
        NewsLetterJpaEntity newsLetterJpaEntity = newsLetterJpaMapper.toJpaEntity(newsLetter);
        NewsLetterJpaEntity savedNewsLetter = newsLetterRepository.save(newsLetterJpaEntity);
        return newsLetterJpaMapper.toDomain(savedNewsLetter);
    }

    @Override
    public Optional<NewsLetter> findById(Id id) {
        return newsLetterRepository.findById(id.getValue())
            .map(newsLetterJpaMapper::toDomain);
    }

    @Override
    public void deleteById(Id id) {
        newsLetterRepository.deleteById(id.getValue());
    }
}
