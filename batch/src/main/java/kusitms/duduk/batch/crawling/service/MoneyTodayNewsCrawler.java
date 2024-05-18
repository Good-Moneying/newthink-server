package kusitms.duduk.batch.crawling.service;

import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
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
public class MoneyTodayNewsCrawler implements NewsCrawler{

    private String CRAWLING_DRIVER_URL = "http://selenium:4444/wd/hub";
    private String TARGET_URL = "https://news.einfomax.co.kr/news/articleList.html?sc_section_code=S1N15&view_type=sm";

    @Override
    public CrawlingNewsResponse crawl() throws InterruptedException, MalformedURLException {
        log.debug("크롤링을 진행합니다.");
        WebDriver driver = null;

        CrawlingNewsResponse crawlingNewsResponse = null;

        try {
            driver = new RemoteWebDriver(new URL(CRAWLING_DRIVER_URL), new ChromeOptions());
            // 브라우저 드라이버 실행
            driver.get(TARGET_URL);
            Thread.sleep(3000);

            List<WebElement> newsArticles = driver.findElements(By.cssSelector("#section-list .type2 > li"));

            for (WebElement article : newsArticles) {
                List<WebElement> images = article.findElements(By.cssSelector("a.thumb img"));
                if (!images.isEmpty()) {
                    WebElement titleElement = article.findElement(By.cssSelector("h4.titles a"));
                    String title = titleElement.getText();
                    String description = article.findElement(By.cssSelector("p.lead")).getText();
                    String thumbnailUrl = images.get(0).getAttribute("src");

                    crawlingNewsResponse = CrawlingNewsResponse.builder()
                            .title(title)
                            .content(description)
                            .thumbnailURL(thumbnailUrl)
                            .build();

                    break;
                }
            }
        }catch (MalformedURLException e) {
            log.error(e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
        return crawlingNewsResponse;
    }
}
