package kusitms.duduk.batch.util;

import kusitms.duduk.batch.dto.crawling.CrawlingNews;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CrawlingUtils {

    @Value("${crawling.driver_path}")
    private String CRAWLING_DRIVER_PATH;

    public CrawlingNews getCrawlingNews(String target_url) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        // 현재 package의 workspace 경로
        Path path = Paths.get(CRAWLING_DRIVER_PATH);

        // WebDriver 경로 설정
        // EC2에 어떻게 올릴지 
        System.setProperty("webdriver.chrome.driver", path.toString());

        // 브라우저 드라이버 실행
        driver.get(target_url);
        Thread.sleep(1000);

        //경제 파트로 이동
        WebElement webElement = driver.findElement(By.cssSelector("a[data-menu-id='economy']"));
        webElement.click();

        // news list 가져오기
        WebElement webElementNewsList= driver.findElement(By.cssSelector(".news-list"));
        List<WebElement> newsList = webElementNewsList.findElements(By.cssSelector(".txt-cont"));

        //dto mapping
        return CrawlingNews.builder()
                .title(newsList.get(0).findElement(By.cssSelector(".news-tit")).getAttribute("innerText"))
                .content(newsList.get(0).findElement(By.cssSelector(".lead")).getAttribute("innerText"))
                .build();
    }
}