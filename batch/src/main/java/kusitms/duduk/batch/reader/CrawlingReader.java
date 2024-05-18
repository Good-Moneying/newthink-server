//package kusitms.duduk.batch.reader;
//
//import kusitms.duduk.core.crawler.dto.response.CrawlingNewsResponse;
////import kusitms.duduk.batch.crawling.service.KoreaEconomyNewsCrawler;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class CrawlingReader implements ItemReader<CrawlingNewsResponse> {
//
//    private String TARGET_URL = "https://www.hankyung.com/all-news";
//
//    //private final KoreaEconomyNewsCrawler koreaEconomyNewsCrawler;
//
////    @Override
////    public CrawlingNewsResponse read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
////        CrawlingNewsResponse crawlingNews = koreaEconomyNewsCrawler.crawl();
////        return crawlingNews;
////    }
//}
