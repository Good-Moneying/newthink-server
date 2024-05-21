package kusitms.duduk.core.crawling.port.output;

import java.net.MalformedURLException;
import kusitms.duduk.core.crawling.dto.response.CrawlingNewsResponse;

public interface NewsCrawlingPort {
    CrawlingNewsResponse crawl() throws InterruptedException, MalformedURLException;
}
