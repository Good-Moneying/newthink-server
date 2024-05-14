package kusitms.duduk.batch.reader;

import kusitms.duduk.batch.dto.crawling.CrawlingNews;
import kusitms.duduk.batch.util.CrawlingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CrawlingReader implements ItemReader<CrawlingNews> {

    @Value("${crawling.target-url}")
    private String TARGET_URL;

    private final CrawlingUtils crawlingUtils;

    @Override
    public CrawlingNews read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return crawlingUtils.getCrawlingNews(TARGET_URL);
    }
}
