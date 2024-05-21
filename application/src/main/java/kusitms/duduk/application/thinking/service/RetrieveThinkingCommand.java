package kusitms.duduk.application.thinking.service;

import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.thinking.dto.ThinkingDtoMapper;
import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingDetailResponse;
import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingHomeResponse;
import kusitms.duduk.core.thinking.port.input.RetrieveThinkingQuery;
import kusitms.duduk.core.thinking.port.output.LoadThinkingPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.thinking.Thinking;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class RetrieveThinkingCommand implements RetrieveThinkingQuery {

    private final LoadUserPort loadUserPort;
    private final LoadThinkingPort loadThinkingPort;
    private final ThinkingDtoMapper thinkingDtoMapper;

    @Override
    public RetrieveThinkingHomeResponse retrieveThinkingHome(String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("해당 유저를 찾을 수 없습니다."));

        List<RetrieveThinkingDetailResponse> response = loadThinkingPort.findAllOrderByIsExistAscAndCreatedAtDesc(
	user.getId().getValue())
            .stream()
            .map(thinkingDtoMapper::toDto)
            .collect(Collectors.toList());

        return RetrieveThinkingHomeResponse
            .builder()
            .thinkingDetails(response)
            .build();
    }

    @Override
    public RetrieveThinkingDetailResponse retrieveThinkingDetail(Long thinkingId) {
        log.info("RetrieveThinkingDetailRequest: {}", thinkingId);
        Thinking thinking = loadThinkingPort.findById(thinkingId)
            .orElseThrow(() -> new NotExistsException("해당 생각을 찾을 수 없습니다."));

        log.info("RetrieveThinkingDetailResponse: {}", thinking.toString());
        return thinkingDtoMapper.toDto(thinking);
    }
}
