package kusitms.duduk.application.thinking.service;

import kusitms.duduk.core.thinking.dto.ThinkingDtoMapper;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingRequest;
import kusitms.duduk.core.thinking.port.input.CreateThinkingUseCase;
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
    private final ThinkingDtoMapper thinkingDtoMapper;

    @Override
    public Thinking create(CreateThinkingRequest request) {
        Thinking thinking = thinkingDtoMapper.create(request);

        return saveThinkingPort.save(thinking);
    }
}
