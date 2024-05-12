package kusitms.duduk.application.newsletter.service;

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
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RetrieveNewsLetterCommand implements RetrieveNewsLetterQuery {

    private final LoadNewsLetterPort loadNewsLetterPort;
    private final NewsLetterDtoMapper newsLetterDtoMapper;

    public NewsLetterResponse retrieve(RetrieveNewsLetterRequest request) {
        NewsLetter newsLetter = loadNewsLetterPort.findById(request.id())
            .orElseThrow(() -> new IllegalArgumentException("해당 뉴스레터를 찾을 수 없습니다."));
        return newsLetterDtoMapper.toDto(newsLetter);
    }

    public NewsLetterThumbnailResponse retriveLatestNewsLetter(User user) {
        NewsLetter newsLetter = loadNewsLetterPort.findLatestNewsLetter()
            .orElseThrow(() -> new IllegalArgumentException("오늘의 뉴스레터를 찾을 수 없습니다."));

        boolean isScrapped = user.isScrapped(newsLetter);
        return newsLetterDtoMapper.toThumbnailDto(newsLetter, isScrapped);
    }
}