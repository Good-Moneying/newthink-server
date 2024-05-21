package kusitms.duduk.application.crawling.service;

import kusitms.duduk.core.crawling.dto.response.CrawlingNewsResponse;
import kusitms.duduk.core.crawling.port.output.NewsCrawlingPort;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

@Slf4j
@Component
public class MailEconomyNewsCrawler implements NewsCrawlingPort {

    private String CRAWLING_DRIVER_URL = "http://selenium:4444/wd/hub";
    private String TARGET_URL = "https://www.mk.co.kr/news/economy/";

    @Override
    public CrawlingNewsResponse crawl() throws InterruptedException, MalformedURLException {
        log.info("크롤링을 진행합니다.");
        WebDriver driver = null;



        return null;
    }
}
