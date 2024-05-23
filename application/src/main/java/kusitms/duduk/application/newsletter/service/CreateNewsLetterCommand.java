package kusitms.duduk.application.newsletter.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import kusitms.duduk.application.newsletter.event.CreateSummaryEvent;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.common.exception.custom.UnauthorizedException;
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
public class CreateNewsLetterCommand implements CreateNewsLetterUseCase {

    private final NewsLetterDtoMapper newsLetterDtoMapper;
    private final SaveNewsLetterPort saveNewsLetterPort;
    private final LoadUserPort loadUserPort;
    private final LoadNewsLetterPort loadNewsLetterPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public NewsLetterResponse create(CreateNewsLetterRequest request) {
        log.info("실시간 뉴스레터를 생성합니다. request : {}", request.toString());
        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(request);
        return processNewsLetter(newsLetter);
    }

    @Override
    public NewsLetterResponse createByEditor(CreateNewsLetterRequest request, String email) {
        log.info("에디터 뉴스레터를 생성합니다. request: {}", request.toString());

        User editor = loadEditorByEmail(email);
        validateEditorAuthorization(editor);

        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(request, editor.getId());
        return processNewsLetter(newsLetter);
    }

    private NewsLetterResponse processNewsLetter(NewsLetter newsLetter) {
        NewsLetter savedNewsLetter = saveNewsLetterPort.create(newsLetter);
        publishEvent(savedNewsLetter);

        NewsLetter loadedNewsLetter = loadNewsLetter(savedNewsLetter.getId().getValue());
        return newsLetterDtoMapper.toDto(loadedNewsLetter);
    }

    private User loadEditorByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
    }

    private void validateEditorAuthorization(User editor) {
        if (!editor.isWritable()) {
            throw new UnauthorizedException(UNAUTHORIZE_EDITOR_ROLE.getMessage());
        }
    }

    private void publishEvent(NewsLetter newsLetter) {
        applicationEventPublisher.publishEvent(new CreateSummaryEvent(this, newsLetter.getId().getValue()));
    }

    private NewsLetter loadNewsLetter(Long newsLetterId) {
        return loadNewsLetterPort.findById(newsLetterId)
            .orElseThrow(() -> new NotExistsException(NEWS_LETTER_NOT_FOUND.getMessage()));
    }
}