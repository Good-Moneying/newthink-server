package kusitms.duduk.application.attendance.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import kusitms.duduk.core.attendance.dto.WeeklyAttendanceResponse;
import kusitms.duduk.core.attendance.port.output.SaveAttendancePort;
import kusitms.duduk.core.user.port.input.AttendUserUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AttendUserCommandTest {

    @Autowired
    private AttendUserUseCase attendUserUseCase;

    @Autowired
    private SaveAttendancePort saveAttendancePort;

    private final String EMAIL = "test@example.com";

    @BeforeEach
    void setup() {
        // 일주일간 날짜 데이터 설정
        LocalDate today = LocalDate.now();
        // 어제
        saveAttendancePort.save(today, EMAIL);
    }

    @Test
    void 일주일_간의_출석_률을_계산_한다() {
        // when
        WeeklyAttendanceResponse response = attendUserUseCase.calculateAttendance(EMAIL);

        // then
        Assertions.assertNotNull(response);
        assertEquals(7, response.data().size());
        assertTrue(response.data().stream().anyMatch(a-> a.isAttendant()  && a.getDayOfWeekValue() == LocalDate.now().getDayOfWeek().getValue()));
    }
}