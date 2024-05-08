package kusitms.duduk.application.newsletter.service;

import kusitms.duduk.core.newsletter.dto.NewsLetterDtoMapper;
import kusitms.duduk.core.newsletter.dto.request.CreateNewsLetterRequest;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterResponse;
import kusitms.duduk.core.newsletter.port.input.CreateNewsLetterUseCase;
import kusitms.duduk.core.newsletter.port.output.SaveNewsLetterPort;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
class CreateNewsLetterCommand implements CreateNewsLetterUseCase {

    private final NewsLetterDtoMapper newsLetterDtoMapper;
    private final SaveNewsLetterPort saveNewsLetterPort;
    private final LoadUserPort loadUserPort;

    @Override
    public NewsLetterResponse create(CreateNewsLetterRequest request) {
        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(request);
        log.info("Create NewsLetter By AI\n request: {}", request.toString());

        return newsLetterDtoMapper.toDto(saveNewsLetterPort.save(newsLetter));
    }

    @Override
    public NewsLetterResponse create(CreateNewsLetterRequest request, String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.isWritable()) {
            throw new IllegalArgumentException("User is not editor");
        }

        // todo 유저 아이디 값을 여기서 세팅
        NewsLetter newsLetter = newsLetterDtoMapper.toDomain(request, user.getId());
        log.info("Create NewsLetter By Editor\n request: {}", request.toString());

        // todo
        // newsLetter.writeBy(user); 해서 여기서 처리?
        return newsLetterDtoMapper.toDto(saveNewsLetterPort.save(newsLetter));
    }
}