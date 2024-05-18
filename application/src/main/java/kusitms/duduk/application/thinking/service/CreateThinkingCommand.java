package kusitms.duduk.application.thinking.service;

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

    @Override
    public Thinking create(CreateThinkingRequest request) {
        Thinking thinking = thinkingDtoMapper.create(request);

        return saveThinkingPort.save(thinking);
    }

    @Override
    public void createThinkingCloud(Long thinkingId, CreateThinkingCloudRequest request) {
        Thinking thinking = loadThinkingPort.findById(thinkingId)
            .orElseThrow(() -> new NotExistsException("해당 생각을 찾을 수 없습니다."));

        thinking.createThinkingCloud(request.sentences());

        saveThinkingPort.save(thinking);
    }
}
