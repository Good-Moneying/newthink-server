package kusitms.duduk.application.user.service;

import kusitms.duduk.core.user.port.input.RetrieveUserQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetrieveUserCommandTest {

    @Autowired
    private RetrieveUserQuery retrieveUserQuery;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void retrieveHome() {

    }
}