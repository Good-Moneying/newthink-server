package kusitms.duduk.batch.crawling.service;

import java.net.MalformedURLException;
import java.net.URL;
import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class KoreaEconomyNewsCrawler implements NewsCrawler {

    private String CRAWLING_DRIVER_URL = "http://selenium:4444/wd/hub";

    //    @Value("${crawling.target-url}")
    private String TARGET_URL = "https://www.hankyung.com/all-news";

    @Override
    public CrawlingNewsResponse crawl() throws InterruptedException, MalformedURLException {
        log.debug("크롤링을 진행합니다.");
        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(CRAWLING_DRIVER_URL), new ChromeOptions());
            // 브라우저 드라이버 실행
            driver.get(TARGET_URL);
            Thread.sleep(3000);

            // 경제 파트로 이동
            WebElement webElement = driver.findElement(By.cssSelector("a[data-menu-id='economy']"));
            webElement.click();

            Thread.sleep(5000);

            // 뉴스 리스트 가져오기
            WebElement webElementNewsList = driver.findElement(By.cssSelector(".economyDiv"));
            List<WebElement> newsList = webElementNewsList.findElements(
	By.cssSelector(".txt-cont"));
            List<WebElement> thumbList = webElementNewsList.findElements(By.cssSelector(".thumb"));

            // dto 매핑
            CrawlingNewsResponse crawlingNews = CrawlingNewsResponse.builder()
	.title(newsList.get(0).findElement(By.cssSelector(".news-tit"))
	    .getAttribute("innerText"))
	.content(
	    newsList.get(0).findElement(By.cssSelector(".lead")).getAttribute("innerText"))
	.thumbnailURL(
	    thumbList.get(0).findElement(By.cssSelector("img")).getAttribute("src"))
	.build();

            return crawlingNews;
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        } finally {
            if (driver != null) {
	driver.quit();
            }
        }

        return null;
    }
}
