package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.CreateAiNewsLetterRequest;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateNewsLetterCommand implements CreateNewsLetterUseCase {

    private final NewsLetterDtoMapper newsLetterDtoMapper;

    @Override
    public NewsLetter create(CreateAiNewsLetterRequest request) {
        return newsLetterDtoMapper.toDomain(request);
    }
}