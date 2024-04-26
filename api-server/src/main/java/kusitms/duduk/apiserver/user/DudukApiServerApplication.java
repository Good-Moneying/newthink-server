package kusitms.duduk.apiserver.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
        "kusitms.duduk.apiserver",
        "kusitms.duduk.domain",
        "kusitms.duduk.core"
})
public class DudukApiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DudukApiServerApplication.class, args);
    }
}
