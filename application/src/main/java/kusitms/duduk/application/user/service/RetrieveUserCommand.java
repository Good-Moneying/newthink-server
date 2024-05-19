package kusitms.duduk.application.user.service;

import java.util.List;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.core.newsletter.port.input.RetrieveNewsLetterQuery;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.core.term.port.input.RetrieveTermQuery;
import kusitms.duduk.core.user.dto.response.RetrieveHomeResponse;
import kusitms.duduk.core.user.dto.response.RetrieveMyPageResponse;
import kusitms.duduk.core.user.dto.response.RetrieveMyPageResponse.ArchiveNewsLetterCount;
import kusitms.duduk.core.user.port.input.AttendUserUseCase;
import kusitms.duduk.core.user.port.input.RetrieveUserQuery;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RetrieveUserCommand implements RetrieveUserQuery {

    private final LoadUserPort loadUserPort;
    private final RetrieveNewsLetterQuery retrieveNewsLetterQuery;
    private final RetrieveTermQuery retrieveTermQuery;
    private final AttendUserUseCase attendUserUseCase;

    @Override
    public boolean isUserRegisteredByEmail(String email) {
        return loadUserPort.existsUserByEmail(email);
    }

    @Override
    public RetrieveHomeResponse home(String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        NewsLetterThumbnailResponse todayNewsLetter = retrieveNewsLetterQuery.retrieveLatestNewsLetter(
            user);
        log.info("오늘의 뉴스레터를 가져온다 : {}", todayNewsLetter);

        List<NewsLetterThumbnailResponse> realtimeTrendNewsLetter = retrieveNewsLetterQuery.retrieveRealtimeTrendNewsLetter(
            user);
        log.info("실시간 트렌드 뉴스레터를 가져온다 : {}", realtimeTrendNewsLetter);

        List<NewsLetterThumbnailResponse> customizeNewsLetter = retrieveNewsLetterQuery.retrieveCustomizeNewsLetter(
            user);
        log.info("커스터마이징 뉴스레터를 가져온다 : {}", customizeNewsLetter);

        RetrieveTermResponse todayTerm = retrieveTermQuery.retrieveLatestTerm(user);
        log.info("오늘의 단어를 가져온다 : {}", todayTerm);

        return RetrieveHomeResponse.builder()
            .todayNewsLetter(todayNewsLetter)
            .realtimeTrendNewsLetters(realtimeTrendNewsLetter)
            .customizeNewsLetters(customizeNewsLetter)
            .todayTerm(todayTerm)
            .build();
    }

    @Override
    public RetrieveMyPageResponse mypage(String email) {
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        List<ArchiveNewsLetterCount> archivesCount = user.getArchives().stream()
            .map(archive -> new ArchiveNewsLetterCount(archive.getCategory(),
	archive.getNewsLetterIds().size()))
            .toList();

        return RetrieveMyPageResponse.builder()
            .nickname(user.getNickname().getValue())
            .reward(user.getReward().getCount())
            .attendances(attendUserUseCase.calculateAttendance(email))
            .counts(archivesCount)
            .build();
    }
}
