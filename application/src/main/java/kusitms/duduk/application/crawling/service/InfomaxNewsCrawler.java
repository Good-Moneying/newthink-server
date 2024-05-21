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
public class InfomaxNewsCrawler implements NewsCrawlingPort {

    private String CRAWLING_DRIVER_URL = "http://selenium:4444/wd/hub";
    private String TARGET_URL = "https://news.einfomax.co.kr/news/articleList.html?sc_section_code=S1N2&view_type=sm";

    @Override
    public CrawlingNewsResponse crawl() throws InterruptedException, MalformedURLException {
        log.debug("크롤링을 진행합니다.");
        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(CRAWLING_DRIVER_URL), new ChromeOptions());
            // 브라우저 드라이버 실행
            driver.get(TARGET_URL);
            Thread.sleep(3000);

            List<WebElement> webElementList = driver.findElements(By.cssSelector("ul.latest_news_list li"));

            for (WebElement webElement : webElementList) {
                try {
                    String thumbnailUrl = webElement.findElement(By.cssSelector("a>img")).getAttribute("src");
                    String title = webElement.findElement(By.cssSelector("div>h4>a")).getText();
                    String content = webElement.findElement(By.cssSelector("div>p>a")).getText();

                    CrawlingNewsResponse crawlingNewsResponse = CrawlingNewsResponse.builder()
                            .title(title)
                            .content(content)
                            .thumbnailURL(thumbnailUrl)
                            .build();

                    if (driver != null) {
                        driver.quit();
                    }

                    return crawlingNewsResponse;
                } catch (NoSuchElementException e) {
                }
            }
        }catch (MalformedURLException e) {
            log.error(e.getMessage());
        }finally {
            if (driver != null) {
                driver.quit();
            }
        }
        return null;
    }
}