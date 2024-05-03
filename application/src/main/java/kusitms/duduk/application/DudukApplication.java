package kusitms.duduk.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
    "kusitms.duduk.application",
    "kusitms.duduk.domain",
    "kusitms.duduk.core"
})
public class DudukApplication {

    public static void main(String[] args) {
        SpringApplication.run(DudukApplication.class, args);
    }
}
