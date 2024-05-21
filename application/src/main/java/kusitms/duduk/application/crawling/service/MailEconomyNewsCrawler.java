package kusitms.duduk.application.crawling.service;

import kusitms.duduk.core.crawling.dto.response.CrawlingNewsResponse;
import kusitms.duduk.core.crawling.port.output.NewsCrawlingPort;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Slf4j
@Component
public class MailEconomyNewsCrawler implements NewsCrawlingPort {

    private String CRAWLING_DRIVER_URL = "http://selenium:4444/wd/hub";
    private String TARGET_URL = "https://www.mk.co.kr/news/economy/";

    @Override
    public CrawlingNewsResponse crawl() throws InterruptedException, MalformedURLException {
        log.debug("크롤링을 진행합니다.");
        WebDriver driver = null;
        CrawlingNewsResponse crawlingNewsResponse = null;

        try {
            driver = new RemoteWebDriver(new URL(CRAWLING_DRIVER_URL), new ChromeOptions());

            driver.get(TARGET_URL);
            Thread.sleep(3000);

            // todo
            List<WebElement> webElementList = driver.findElements(By.cssSelector("ul.latest_news_list li"));

            for (WebElement webElement : webElementList) {
                try {
                    //todo
                    String thumbnailUrl = webElement.findElement(By.cssSelector("a>div.thumb_area>img")).getAttribute("src");
                    String title = webElement.findElement(By.cssSelector("a>div.txt_area>h3")).getText();
                    String content = webElement.findElement(By.cssSelector("a>div.txt_area>p")).getText();

                    System.out.println("섬네일 " + thumbnailUrl);
                    System.out.println("제목 " + title);
                    System.out.println("내용 " + content);

                    crawlingNewsResponse = CrawlingNewsResponse.builder()
                            .title(title)
                            .content(content)
                            .thumbnailURL(thumbnailUrl)
                            .build();

                    if (driver != null) {
                        driver.quit();
                    }

                    return crawlingNewsResponse;
                } catch (NoSuchElementException ignored) {
                }
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
        return null;
    }
}
