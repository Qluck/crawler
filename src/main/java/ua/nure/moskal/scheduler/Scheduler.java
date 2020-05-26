package ua.nure.moskal.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.nure.moskal.crawler.BershkaPageProcessor;
import ua.nure.moskal.crawler.HttpClientDownloader;
import ua.nure.moskal.crawler.MySqlPipeline;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.QueueScheduler;

@Service
@Slf4j
public class Scheduler {
    @Autowired
    @Qualifier(value = "mySqlPipeline")
    private Pipeline pipeline;

    @Scheduled(cron = "*/10 * * * * *")
    public void crawler() {
        log.info("start");
        try {
            System.setProperty("https.protocols", "SSLv3, TLSv1, TLSv1.1, TLSv1.2");
            Spider.create(new BershkaPageProcessor())
                    //From "https://github.com/code4craft" began to grasp
                    /// URL адреса "https://github.com/code4craft" з якої починається пошук
                    .addUrl("https://www.bershka.com/ua/men/collection/new-c1010378021.html")
                    //Open 5 threads of Crawler
                    /// Відкрвати 5 паралельних потоків пошукача
                    .thread(10)
                    .setDownloader(new HttpClientDownloader())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .setScheduler(new QueueScheduler())
                    //Start Crawler
                    /// Запуск в роботу пошукача
                    .run();
        } catch (Exception ex) {
            log.info("Error");
        }
        log.info("finish");
    }
}
