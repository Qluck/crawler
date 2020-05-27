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
        page.putField("image", html.regex("img\\ssrc\\s*=\\s*\"(https.+?)\"\\salt=\"(.+?)\".+?(\\d+).+?href=\"(.+?)\"", 1).all());
        page.putField("name", html.regex("img\\ssrc\\s*=\\s*\"(https.+?)\"\\salt=\"(.+?)\".+?(\\d+).+?href=\"(.+?)\"", 2).all());
        page.putField("price", html.regex("img\\ssrc\\s*=\\s*\"(https.+?)\"\\salt=\"(.+?)\".+?(\\d+).+?href=\"(.+?)\"", 3).all());
        page.putField("redirect_url", html.regex("img\\ssrc\\s*=\\s*\"(https.+?)\"\\salt=\"(.+?)\".+?(\\d+).+?href=\"(.+?)\"", 4).all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
