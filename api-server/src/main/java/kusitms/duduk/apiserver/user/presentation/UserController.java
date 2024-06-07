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
import kusitms.duduk.core.user.port.input.WithdrawUserUseCase;
import kusitms.duduk.domain.global.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final WithdrawUserUseCase withdrawUserUseCase;
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

    @DeleteMapping
    public ResponseEntity<Void> withdraw(CustomUserDetails customUserDetails) {
        withdrawUserUseCase.withdraw(customUserDetails.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<RetrieveMyPageResponse> mypage(
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return new ResponseEntity<>(retrieveUserQuery.mypage(customUserDetails.getEmail()),
            HttpStatus.OK);
    }
}

