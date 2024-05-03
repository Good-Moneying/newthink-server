package kusitms.duduk.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "kusitms.duduk.domain",
    "kusitms.duduk.core"
})
public class DudukCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DudukCoreApplication.class, args);
    }
}
