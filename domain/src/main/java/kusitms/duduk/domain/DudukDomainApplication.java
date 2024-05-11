package kusitms.duduk.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "kusitms.duduk.domain",
    "kusitms.duduk.common"
})

public class DudukDomainApplication {
    public static void main(String[] args) {
        SpringApplication.run(DudukDomainApplication.class, args);
    }
}
