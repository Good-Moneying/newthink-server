package kusitms.duduk.apiserver.healthcheck.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HealthCheckController implements HealthCheckControllerDocs {

    private final Environment environment;

    @Override
    public String healthCheck() {
        return environment.getProperty("spring.application.name") + " is running on port: "
            + environment.getProperty("local.server.port");
    }
}
