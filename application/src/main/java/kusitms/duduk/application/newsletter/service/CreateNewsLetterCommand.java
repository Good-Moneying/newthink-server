package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.application.newsletter.event.CreateSummaryEvent;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.LoadNewsLetterPort;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
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
// todo : @Transactional 여부에 따라 어떻게 LazyInitializationException이 발생하는지 확인할 소요가 있음
class CreateNewsLetterCommand implements CreateNewsLetterUseCase {

    private final NewsLetterDtoMapper newsLetterDtoMapper;
    private final SaveNewsLetterPort saveNewsLetterPort;
    private final LoadUserPort loadUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public NewsLetterResponse create(CreateNewsLetterRequest request) {
        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(request);
        log.info("Create NewsLetter By AI\n request: {}", request.toString());
        NewsLetter savedNewsLetter = saveNewsLetterPort.create(newsLetterDtoMapper.toDomain(request));

        applicationEventPublisher.publishEvent(new CreateSummaryEvent(this, newsLetter.getId().getValue()));

        NewsLetter loadedNewsLetter = loadNewsLetterPort.findById(savedNewsLetter.getId().getValue())
            .orElseThrow(() -> new NotExistsException("NewsLetter not found"));

        return newsLetterDtoMapper.toDto(loadedNewsLetter);
    }

    @Override
    public NewsLetterResponse create(CreateNewsLetterRequest request, String email) {
        User editor = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!editor.isWritable()) {
            throw new IllegalArgumentException("User is not editor");
        }

        NewsLetter savedNewsLetter = saveNewsLetterPort.create(newsLetterDtoMapper.toDomain(request, editor.getId()));
        log.info("Create NewsLetter By Editor\n request: {}", request.toString());

        applicationEventPublisher.publishEvent(new CreateSummaryEvent(this, savedNewsLetter.getId().getValue()));

        NewsLetter loadedNewsLetter = loadNewsLetterPort.findById(savedNewsLetter.getId().getValue())
            .orElseThrow(() -> new NotExistsException("NewsLetter not found"));

        return newsLetterDtoMapper.toDto(loadedNewsLetter);
    }
}