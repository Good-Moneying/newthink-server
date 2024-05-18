package kusitms.duduk.batch.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class SeleniumConfig {

    @Value("${selenium.url}")
    private String seleniumUrl;

    @Bean
    public WebDriver webDriver() throws Exception {
        ChromeOptions options = new ChromeOptions();
        return new RemoteWebDriver(new URL(seleniumUrl), options);
    }
}
