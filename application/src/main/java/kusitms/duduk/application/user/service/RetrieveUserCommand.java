package kusitms.duduk.application.user.service;

import static kusitms.duduk.common.exception.ErrorMessage.*;

import java.util.List;
import kusitms.duduk.common.exception.custom.NotExistsException;
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
    public String retrieveUserNicknameByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .map(user -> user.getNickname().getValue())
            .orElse(null);
    }

    @Override
    public RetrieveHomeResponse home(String email) {
        User user = getUserByEmail(email);

        NewsLetterThumbnailResponse todayNewsLetter = retrieveTodayNewsLetter(user);
        List<NewsLetterThumbnailResponse> realtimeTrendNewsLetter = retrieveRealtimeTrendNewsLetter(user);
        List<NewsLetterThumbnailResponse> customizeNewsLetter = retrieveCustomizeNewsLetter(user);
        RetrieveTermResponse todayTerm = retrieveTodayTerm(user);

        return RetrieveHomeResponse.builder()
            .todayNewsLetter(todayNewsLetter)
            .realtimeTrendNewsLetters(realtimeTrendNewsLetter)
            .customizeNewsLetters(customizeNewsLetter)
            .todayTerm(todayTerm)
            .build();
    }

    @Override
    public RetrieveMyPageResponse mypage(String email) {
        User user = getUserByEmail(email);

        List<ArchiveNewsLetterCount> archivesCount = getTop4Archives(user);

        return RetrieveMyPageResponse.builder()
            .level(user.getLevel().name())
            .profileUrl(user.getLevel().getProfileUrl())
            .nickname(user.getNickname().getValue())
            .reward(user.getReward().getCount())
            .follower(user.getFollower().getCount())
            .followee(user.getFollowee().getCount())
            .attendances(attendUserUseCase.calculateAttendance(email))
            .counts(archivesCount)
            .build();
    }

    private User getUserByEmail(String email) {
        return loadUserPort.findByEmail(email)
            .orElseThrow(() -> new NotExistsException(USER_NOT_FOUND.getMessage()));
    }

    private NewsLetterThumbnailResponse retrieveTodayNewsLetter(User user) {
        NewsLetterThumbnailResponse todayNewsLetter = retrieveNewsLetterQuery.retrieveLatestNewsLetter(user);
        log.info("Retrieved today's newsletter: {}", todayNewsLetter);
        return todayNewsLetter;
    }

    private List<NewsLetterThumbnailResponse> retrieveRealtimeTrendNewsLetter(User user) {
        List<NewsLetterThumbnailResponse> realtimeTrendNewsLetter = retrieveNewsLetterQuery.retrieveRealtimeTrendNewsLetter(user);
        log.info("Retrieved real-time trend newsletters: {}", realtimeTrendNewsLetter);
        return realtimeTrendNewsLetter;
    }

    private List<NewsLetterThumbnailResponse> retrieveCustomizeNewsLetter(User user) {
        List<NewsLetterThumbnailResponse> customizeNewsLetter = retrieveNewsLetterQuery.retrieveCustomizeNewsLetter(user);
        log.info("Retrieved customized newsletters: {}", customizeNewsLetter);
        return customizeNewsLetter;
    }

    private RetrieveTermResponse retrieveTodayTerm(User user) {
        RetrieveTermResponse todayTerm = retrieveTermQuery.retrieveLatestTerm(user);
        log.info("Retrieved today's term: {}", todayTerm);
        return todayTerm;
    }

    private List<ArchiveNewsLetterCount> getTop4Archives(User user) {
        return user.getArchives().stream()
            .filter(archive -> !archive.getCategory().name().equals("WORD"))
            .map(archive -> new ArchiveNewsLetterCount(
                archive.getCategory().getDescription(),
                archive.getCategory().getLogoUrl(),
                archive.getNewsLetterIds().size()))
            .sorted((a, b) -> Integer.compare(b.getCount(), a.getCount()))
            .limit(4)
            .toList();
    }
}
