package kusitms.duduk.batch.crawling.service;

import java.net.MalformedURLException;
import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;

public interface NewsCrawler {
    public CrawlingNewsResponse crawl() throws InterruptedException, MalformedURLException;
}
