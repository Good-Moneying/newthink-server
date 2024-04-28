package kusitms.duduk.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DudukDomainApplication {
    public static void main(String[] args) {
        SpringApplication.run(DudukDomainApplication.class, args);
    }
}

