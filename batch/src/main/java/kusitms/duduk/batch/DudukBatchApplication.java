package kusitms.duduk.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {
    "kusitms.duduk.batch",
    "kusitms.duduk.domain",
    "kusitms.duduk.core"
})
public class DudukBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DudukBatchApplication.class, args);
    }
}
