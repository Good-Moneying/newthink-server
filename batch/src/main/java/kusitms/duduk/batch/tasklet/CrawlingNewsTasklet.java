package kusitms.duduk.batch.tasklet;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.core.crawling.port.output.NewsCrawlingPort;
import kusitms.duduk.core.crawling.dto.response.CrawlingNewsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CrawlingNewsTasklet implements Tasklet {

    private final List<NewsCrawlingPort> newsCrawlerList;
    private List<CrawlingNewsResponse> crawlingNewsResponses;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
        throws MalformedURLException, InterruptedException {
        crawlingNewsResponses = new ArrayList<>();
        for (NewsCrawlingPort newsCrawler : newsCrawlerList) {
            CrawlingNewsResponse response = newsCrawler.crawl();
            crawlingNewsResponses.add(response);
        }
        chunkContext.getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().put("crawlingNewsResponses", crawlingNewsResponses);
        log.info("Crawled {} news articles", crawlingNewsResponses.size());
        return RepeatStatus.FINISHED;
    }
}