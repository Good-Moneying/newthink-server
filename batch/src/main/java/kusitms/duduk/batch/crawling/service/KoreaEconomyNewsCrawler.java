package kusitms.duduk.batch.crawling.service;

import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KoreaEconomyNewsCrawler implements NewsCrawler{

    @Value("${crawling.driver_path}")
    private String CRAWLING_DRIVER_PATH;

    @Value("${crawling.target-url}")
    private String TARGET_URL;

    private final WebDriver driver;

    @Override
    public CrawlingNewsResponse crawl() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", TARGET_URL);

        // 브라우저 드라이버 실행
        driver.get(TARGET_URL);
        Thread.sleep(3000);

        //경제 파트로 이동
        WebElement webElement = driver.findElement(By.cssSelector("a[data-menu-id='economy']"));
        webElement.click();

        Thread.sleep(5000);

        // news list 가져오기
        WebElement webElementNewsList= driver.findElement(By.cssSelector(".economyDiv"));
        List<WebElement> newsList = webElementNewsList.findElements(By.cssSelector(".txt-cont"));
        List<WebElement> thumbList = webElementNewsList.findElements(By.cssSelector(".thumb"));

        //dto mapping
        CrawlingNewsResponse crawlingNews = CrawlingNewsResponse.builder()
                .title(newsList.get(0).findElement(By.cssSelector(".news-tit")).getAttribute("innerText"))
                .content(newsList.get(0).findElement(By.cssSelector(".lead")).getAttribute("innerText"))
                .thumbnailURL(thumbList.get(0).findElement(By.cssSelector("img")).getAttribute("src"))
                .build();

        driver.quit();
        System.clearProperty("webdriver.chrome.driver");

        return crawlingNews;
    }
}