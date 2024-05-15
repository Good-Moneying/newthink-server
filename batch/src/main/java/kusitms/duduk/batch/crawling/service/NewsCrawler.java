package kusitms.duduk.batch.crawling.service;

import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;

public interface NewsCrawler {
    public CrawlingNewsResponse crawl() throws InterruptedException;
}
