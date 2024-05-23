package kusitms.duduk.application.thinking.service;

import kusitms.duduk.application.thinking.event.CreateThinkingEvent;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.thinking.dto.ThinkingDtoMapper;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingCloudRequest;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.core.thinking.port.input.CreateThinkingUseCase;
import kusitms.duduk.core.thinking.port.output.LoadThinkingPort;
import kusitms.duduk.core.thinking.port.output.SaveThinkingPort;
import kusitms.duduk.domain.thinking.Thinking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CreateThinkingCommand implements CreateThinkingUseCase {

    private final SaveThinkingPort saveThinkingPort;
    private final LoadThinkingPort loadThinkingPort;
    private final ThinkingDtoMapper thinkingDtoMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Thinking create(CreateThinkingRequest request) {
        log.info("생각 생성 : {}", request);
        Thinking thinking = thinkingDtoMapper.create(request);

        return saveThinkingPort.save(thinking);
    }

    @Override
    public void createThinkingCloud(Long thinkingId, CreateThinkingCloudRequest request) {
        log.info("생각 구름 생성 : thinkingId={}, request={}", thinkingId, request);
        Thinking thinking = loadThinkingById(thinkingId);

        thinking.createThinkingCloud(request.sentences());
        publishEvent(thinking);

        saveThinkingPort.save(thinking);
    }

    private Thinking loadThinkingById(Long thinkingId) {
        return loadThinkingPort.findById(thinkingId)
            .orElseThrow(() -> new NotExistsException("해당 생각을 찾을 수 없습니다."));
    }

    private void publishEvent(Thinking thinking) {
        log.info("Create Thinking Event 발행 : thinkingId={}, userId={}", thinking.getId().getValue(),
            thinking.getUserId().getValue());

        applicationEventPublisher.publishEvent(
            new CreateThinkingEvent(this, thinking.getId().getValue(),
	thinking.getUserId().getValue()));
    }
}