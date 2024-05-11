package kusitms.duduk.application.term.service;

import kusitms.duduk.core.term.dto.TermDtoMapper;
import kusitms.duduk.core.term.dto.request.CreateTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.core.term.port.input.CreateTermUseCase;
import kusitms.duduk.core.term.port.output.SaveUserPort;
import kusitms.duduk.domain.term.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateTermCommand implements CreateTermUseCase {

    private final SaveUserPort createTermPort;
    private final TermDtoMapper termDtoMapper;

    @Override
    public RetrieveTermResponse create(CreateTermRequest request) {
        Term term = termDtoMapper.toDomain(request);

        // todo : 추후에 관리자만 생성할 수 있도록 추가
        return termDtoMapper.toDto(createTermPort.save(term));
    }
}
