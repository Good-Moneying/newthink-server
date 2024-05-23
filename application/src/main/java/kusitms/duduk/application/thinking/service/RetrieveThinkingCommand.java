package kusitms.duduk.application.thinking.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import java.util.List;
import java.util.stream.Collectors;
import kusitms.duduk.common.exception.ErrorMessage;
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
        User user = loadUserByEmail(email);
        List<RetrieveThinkingDetailResponse> thinkingDetails = getThinkingDetails(user);

        return RetrieveThinkingHomeResponse.builder()
            .thinkingDetails(thinkingDetails)
            .build();
    }

    private User loadUserByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
    }

    private Thinking loadThinkingById(Long thinkingId) {
        return loadThinkingPort.findById(thinkingId)
            .orElseThrow(() -> new NotExistsException(THINKING_NOT_FOUND.getMessage()));
    }

    private List<RetrieveThinkingDetailResponse> getThinkingDetails(User user) {
        log.info("사용자 {}의 생각 리스트 조회", user.getId().getValue());
        return loadThinkingPort.findAllOrderByIsExistAscAndCreatedAtDesc(user.getId().getValue())
            .stream()
            .map(thinkingDtoMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public RetrieveThinkingDetailResponse retrieveThinkingDetail(Long thinkingId) {
        Thinking thinking = loadThinkingById(thinkingId);

        return thinkingDtoMapper.toDto(thinking);
    }
}
