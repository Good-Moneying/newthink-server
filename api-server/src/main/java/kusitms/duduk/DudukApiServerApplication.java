package kusitms.duduk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
        "kusitms.duduk"
})
public class DudukApiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DudukApiServerApplication.class, args);
    }
}
