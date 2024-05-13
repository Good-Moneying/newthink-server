package kusitms.duduk.application.term.service;

import kusitms.duduk.core.term.dto.TermDtoMapper;
import kusitms.duduk.core.term.dto.request.RetrieveTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.core.term.port.input.RetrieveTermQuery;
import kusitms.duduk.core.term.port.output.LoadTermPort;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RetrieveTermCommand implements RetrieveTermQuery {

    private final LoadTermPort loadTermPort;
    private final TermDtoMapper termDtoMapper;

    @Override
    public RetrieveTermResponse retrieve(RetrieveTermRequest request) {
        Term term = loadTermPort.findById(request.termId())
            .orElseThrow(() -> new IllegalArgumentException("해당 단어를 찾을 수 없습니다."));

        return termDtoMapper.toDto(term);
    }

    @Override
    public RetrieveTermResponse retrieveLatestTerm(User user) {
        Term term = loadTermPort.findLatestTerm()
            .orElseThrow(() -> new IllegalArgumentException("최신 단어를 찾을 수 없습니다."));

        boolean isScrapped = user.isScrapped(term);

        return termDtoMapper.toResponse(term, isScrapped);
    }
}
