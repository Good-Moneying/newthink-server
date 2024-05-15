package kusitms.duduk.application.newsletter.service;

import java.util.List;
import kusitms.duduk.application.newsletter.event.RetrieveNewsLetterEvent;
import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.RetrieveNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.core.newsletter.port.input.RetrieveNewsLetterQuery;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class RetrieveNewsLetterCommand implements RetrieveNewsLetterQuery {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final NewsLetterDtoMapper newsLetterDtoMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public NewsLetterResponse retrieveNewsLetterDetail(RetrieveNewsLetterRequest request) {
        NewsLetter newsLetter = loadNewsLetterPort.findById(request.newsLetterId())
            .orElseThrow(() -> new IllegalArgumentException("해당 뉴스레터를 찾을 수 없습니다."));

        // 뉴스레터 조회 이벤트 발생
        applicationEventPublisher.publishEvent(
            new RetrieveNewsLetterEvent(this, newsLetter.getNewsLetterId().getValue()));

        // 다시 로드
        NewsLetter savedNewsLetter = loadNewsLetterPort.findById(request.newsLetterId())
            .orElseThrow(() -> new IllegalArgumentException("해당 뉴스레터를 찾을 수 없습니다."));

        return newsLetterDtoMapper.toDto(savedNewsLetter);
    }

    public NewsLetterThumbnailResponse retrieveLatestNewsLetter(User user) {
        NewsLetter newsLetter = loadNewsLetterPort.findLatestNewsLetter()
            .orElseThrow(() -> new IllegalArgumentException("오늘의 뉴스레터를 찾을 수 없습니다."));

        boolean isScrapped = user.isScrapped(newsLetter);
        return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
    }

    @Override
    public List<NewsLetterThumbnailResponse> retrieveRealtimeTrendNewsLetter(User user) {
        return loadNewsLetterPort.findRealtimeTrendNewsLetters().stream()
            .map(newsLetter -> {
	boolean isScrapped = user.isScrapped(newsLetter);
	return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
            })
            .toList();
    }

    @Override
    public List<NewsLetterThumbnailResponse> retrieveCustomizeNewsLetter(User user) {
        return loadNewsLetterPort.findCustomizeNewsLetters(user.getCategory())
            .stream()
            .map(newsLetter -> {
	boolean isScrapped = user.isScrapped(newsLetter);
	return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
            })
            .toList();
    }
}