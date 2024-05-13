package kusitms.duduk.application.newsletter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.common.annotation.Adapter;
import kusitms.duduk.core.newsletter.port.output.DeleteNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.UpdateNewsLetterPort;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class NewsLetterPersistenceAdapter implements LoadNewsLetterPort, DeleteNewsLetterPort,
    SaveNewsLetterPort, UpdateNewsLetterPort {

    private final NewsLetterRepository newsLetterRepository;
    private final NewsLetterJpaMapper newsLetterJpaMapper;

    @Override
    public NewsLetter create(NewsLetter newsLetter) {
        NewsLetterJpaEntity newsLetterJpaEntity = newsLetterJpaMapper.toJpaEntity(newsLetter);
        NewsLetterJpaEntity savedNewsLetter = newsLetterRepository.save(newsLetterJpaEntity);
        return newsLetterJpaMapper.toDomain(savedNewsLetter);
    }

    @Override
    public void saveAndFlush(NewsLetter newsLetter) {
        NewsLetterJpaEntity newsLetterJpaEntity = newsLetterJpaMapper.toJpaEntity(newsLetter);
        newsLetterRepository.saveAndFlush(newsLetterJpaEntity);
    }

    @Override
    public Optional<NewsLetter> findById(Long id) {
        return newsLetterRepository.findById(id)
            .map(newsLetterJpaMapper::toDomain);
    }

    @Override
    public Optional<NewsLetter> findLatestNewsLetter() {
        return newsLetterRepository.findLatestNewsLetter()
            .map(newsLetterJpaMapper::toDomain);
    }

    @Override
    public List<NewsLetter> findAll() {
        return newsLetterJpaMapper.toDomainList(newsLetterRepository.findAll());
    }

    @Override
    public List<NewsLetter> findRealtimeTrendNewsLetters() {
        return newsLetterRepository.findRealtimeTrendNewsLetters()
            .stream()
            .map(newsLetterJpaMapper::toDomain)
            .collect(Collectors.toList());

    }

    @Override
    public List<NewsLetter> findCustomizeNewsLetters(Category category) {
        return newsLetterRepository.findCustomizeNewsLetters(category)
            .stream()
            .map(newsLetterJpaMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        newsLetterRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        newsLetterRepository.deleteAll();
    }
}
