package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.RetrieveNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.port.input.RetrieveNewsLetterQuery;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
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
}