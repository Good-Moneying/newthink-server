package kusitms.duduk.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@EnableBatchProcessing
@SpringBootApplication(scanBasePackages = {
    "kusitms.duduk.batch",
    "kusitms.duduk.application",
    "kusitms.duduk.domain",
    "kusitms.duduk.core",
    "kusitms.duduk.common"})
public class DudukBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DudukBatchApplication.class, args);
    }
}
