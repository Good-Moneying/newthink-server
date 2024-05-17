package kusitms.duduk.application.thinking.service;

import java.util.List;
import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.thinking.port.input.RetrieveThinkingQuery;
import kusitms.duduk.core.thinking.port.output.LoadThinkingPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.thinking.Thinking;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class RetrieveThinkingCommand implements RetrieveThinkingQuery {

    private final LoadUserPort loadUserPort;
    private final LoadThinkingPort loadThinkingPort;

    @Override
    public List<Thinking> retrieveThinkingHome(String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("해당 유저를 찾을 수 없습니다."));

        return loadThinkingPort.findAllOrderByIsExistAscAndCreatedAtAsc(user.getId().getValue());
    }
}
