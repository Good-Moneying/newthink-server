package kusitms.duduk.application.attendant.service;

import java.time.LocalDate;
import kusitms.duduk.core.attendant.port.input.DateProvider;
import org.springframework.stereotype.Component;

@Component
public class SystemDateProvider implements DateProvider {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
