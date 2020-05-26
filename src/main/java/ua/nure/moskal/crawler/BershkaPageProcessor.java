package ua.nure.moskal.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class BershkaPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = new Html(page.getRawText());
        page.putField("image", html.regex("img\\ssrc\\s*=\\s*\"(https.+?)\"\\salt=\"(.+?)\".+?(\\d+)", 1).all());
        page.putField("name", html.regex("img\\ssrc\\s*=\\s*\"(https.+?)\"\\salt=\"(.+?)\".+?(\\d+)", 2).all());
        page.putField("price", html.regex("img\\ssrc\\s*=\\s*\"(https.+?)\"\\salt=\"(.+?)\".+?(\\d+)", 3).all());

        // Part III: From the subsequent discovery page url address to crawler
        /// Частина 3: Із результатів попередніх обробок вишукуемо URL адреси інших сторінок для передачи пошукачеві на обробку
        //     page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\w\-]+/[\w\-]+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
