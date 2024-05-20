package kusitms.duduk.application.term.service;

import kusitms.duduk.common.exception.custom.NotExistsException;
import kusitms.duduk.core.term.dto.response.ArchiveTermResponse;
import kusitms.duduk.core.term.port.input.ArchiveTermUseCase;
import kusitms.duduk.core.term.port.output.LoadTermPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.core.user.port.output.UpdateUserPort;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ArchiveTermCommand implements ArchiveTermUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;
    private final LoadTermPort loadTermPort;

    @Override
    public ArchiveTermResponse archive(String email, Long termId) {
        log.info("archive Term() start\n");
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException("User not found"));

        Term term = loadTermPort.findById(termId)
            .orElseThrow(() -> new NotExistsException("Term not found"));

        user.archiveTerm(term);
        updateUserPort.update(user);
        return new ArchiveTermResponse(termId);
    }
}
