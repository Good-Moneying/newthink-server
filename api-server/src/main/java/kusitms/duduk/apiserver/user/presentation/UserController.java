package kusitms.duduk.apiserver.user.presentation;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse;
import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse.DailyAttendance;
import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.core.user.dto.request.CreateUserRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserEmailRequest;
import kusitms.duduk.core.user.dto.request.ValidateUserNicknameRequest;
import kusitms.duduk.core.user.dto.response.RetrieveHomeResponse;
import kusitms.duduk.core.user.dto.response.RetrieveMyPageResponse;
import kusitms.duduk.core.user.dto.response.UserResponse;
import kusitms.duduk.core.user.port.input.RegisterUserUseCase;
import kusitms.duduk.core.user.port.input.RetrieveUserQuery;
import kusitms.duduk.core.user.port.input.ValidateDuplicatedUserQuery;
import kusitms.duduk.domain.global.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {

    private final RegisterUserUseCase registerUserUseCase;
    private final RetrieveUserQuery retrieveUserQuery;
    private final ValidateDuplicatedUserQuery validateDuplicatedUserQuery;

    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(registerUserUseCase.register(createUserRequest),
            HttpStatus.CREATED);
    }

    @PostMapping("/validate/email")
    public ResponseEntity<Void> validateEmail(@RequestBody ValidateUserEmailRequest request) {
        validateDuplicatedUserQuery.validateDuplicatedEmail(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/nickname")
    public ResponseEntity<Void> validateNickname(@RequestBody ValidateUserNicknameRequest request) {
        validateDuplicatedUserQuery.validateDuplicatedNickname(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<RetrieveHomeResponse> home(CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(retrieveUserQuery.home(customUserDetails.getEmail()),
            HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<RetrieveMyPageResponse> mypage(
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(retrieveUserQuery.mypage(customUserDetails.getEmail()),
            HttpStatus.OK);
    }

    @GetMapping("/home/test")
    public ResponseEntity<RetrieveHomeResponse> homeTest(CustomUserDetails customUserDetails) {
        RetrieveHomeResponse response = RetrieveHomeResponse.builder()
            .todayNewsLetter(NewsLetterThumbnailResponse.builder()
	.id(1L)
	.title("오늘의 주목할 뉴스")
	.type("editor")
	.keywords("테슬라, 주가 변동, 시장 분석")
	.thumbnail(
	    "https://image.shutterstock.com/image-photo/latest-stock-market-charts.jpg")
	.isScrapped(false)
	.createdAt(LocalDateTime.now())
	.build())
            .realtimeTrendNewsLetters(List.of(
	NewsLetterThumbnailResponse.builder()
	    .id(2L)
	    .title("기술 섹터 동향")
	    .type("analysis")
	    .keywords("애플, 삼성, 최신 기술")
	    .thumbnail("https://image.shutterstock.com/image-photo/technology-trends.jpg")
	    .isScrapped(true)
	    .createdAt(LocalDateTime.now().minusHours(3))
	    .build(),
	NewsLetterThumbnailResponse.builder()
	    .id(3L)
	    .title("유럽 시장 개요")
	    .type("global")
	    .keywords("유로존, 경제 성장")
	    .thumbnail("https://image.shutterstock.com/image-photo/european-markets.jpg")
	    .isScrapped(false)
	    .createdAt(LocalDateTime.now().minusHours(2))
	    .build(),
	NewsLetterThumbnailResponse.builder()
	    .id(4L)
	    .title("투자 조언: 금융 포트폴리오 관리")
	    .type("financial")
	    .keywords("투자, 자산 관리")
	    .thumbnail(
	        "https://image.shutterstock.com/image-photo/financial-management.jpg")
	    .isScrapped(true)
	    .createdAt(LocalDateTime.now().minusHours(1))
	    .build()
            ))
            .customizeNewsLetters(List.of(
	NewsLetterThumbnailResponse.builder()
	    .id(5L)
	    .title("지속 가능한 투자 동향")
	    .type("sustainable")
	    .keywords("그린 에너지, 친환경 정책")
	    .thumbnail(
	        "https://image.shutterstock.com/image-photo/sustainable-investments.jpg")
	    .isScrapped(false)
	    .createdAt(LocalDateTime.now().minusDays(1))
	    .build(),
	NewsLetterThumbnailResponse.builder()
	    .id(6L)
	    .title("신흥 시장 뉴스 업데이트")
	    .type("emerging")
	    .keywords("신흥 시장, 경제 발전")
	    .thumbnail("https://image.shutterstock.com/image-photo/emerging-markets.jpg")
	    .isScrapped(true)
	    .createdAt(LocalDateTime.now().minusDays(1).minusHours(2))
	    .build(),
	NewsLetterThumbnailResponse.builder()
	    .id(7L)
	    .title("실리콘 밸리 최신 소식")
	    .type("tech")
	    .keywords("스타트업, 혁신, 기술")
	    .thumbnail("https://image.shutterstock.com/image-photo/silicon-valley.jpg")
	    .isScrapped(false)
	    .createdAt(LocalDateTime.now().minusDays(1).minusHours(4))
	    .build()
            ))
            .todayTerm(RetrieveTermResponse.builder()
	.termId(1L)
	.koreanName("지속 가능성")
	.englishName("Sustainability")
	.description("지속 가능성은 환경적, 경제적, 사회적 측면에서 장기적으로 견딜 수 있는 발전을 의미합니다.")
	.category("ENVIRONMENT")
	.isScrapped(true)
	.build())
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/mypage/test")
    public ResponseEntity<RetrieveMyPageResponse> mypageTest(
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(
            RetrieveMyPageResponse.builder()
	.nickname("백건도리")
	.reward(3)
	.attendances(
	    WeeklyAttendanceResponse.builder()
	        .data(
	            List.of(
		DailyAttendance.of(DayOfWeek.MONDAY, true),
		DailyAttendance.of(DayOfWeek.TUESDAY, true),
		DailyAttendance.of(DayOfWeek.WEDNESDAY, false),
		DailyAttendance.of(DayOfWeek.THURSDAY, false),
		DailyAttendance.of(DayOfWeek.FRIDAY, true),
		DailyAttendance.of(DayOfWeek.SATURDAY, false),
		DailyAttendance.of(DayOfWeek.SUNDAY, false)
	            )
	        )
	        .build()
	)
	.counts(List.of(
	    RetrieveMyPageResponse.ArchiveNewsLetterCount.builder()
	        .category(Category.POLICY)
	        .count(2)
	        .build(),
	    RetrieveMyPageResponse.ArchiveNewsLetterCount.builder()
	        .category(Category.FINANCE)
	        .count(1)
	        .build(),
	    RetrieveMyPageResponse.ArchiveNewsLetterCount.builder()
	        .category(Category.REAL_ESTATE)
	        .count(5)
	        .build(),
	    RetrieveMyPageResponse.ArchiveNewsLetterCount.builder()
	        .category(Category.SECURITIES)
	        .count(8)
	        .build()
	))
	.build(),
            HttpStatus.OK);
    }
}

