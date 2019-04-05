package cn.hd.crawler.core.processor;

import cn.hd.crawler.core.model.CrawlerConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

public class MyProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    // 抓取的信息xpath规则
    private CrawlerConfig crawlerConfig;
    public final int num = 0;

    public MyProcessor(CrawlerConfig crawlerConfig) {
        this.crawlerConfig = crawlerConfig;
    }

    @Override
    public void process(Page page) {
        // 定义如何抽取页面信息，并保存下来
        for (Map.Entry<String, String> entry : crawlerConfig.getXpaths().entrySet()) {
            page.putField(entry.getKey(), page.getHtml().xpath(entry.getValue()).toString());
        }
        // 从页面发现后续的url地址来抓取
        for (String xpath : crawlerConfig.getUrlsXpaths()) {
            page.addTargetRequests(page.getHtml().links().regex(xpath).all());
        }
        // 从页面发现后续的url地址包含条件
        for(String contain : crawlerConfig.getUrlsContain()) {
            page.addTargetRequests(page.getHtml().links().all());
        }
        if(num < 2) {
            return;
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    static {
        // System.setProperty("javax.net.debug", "all");
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    public static void main(String[] args) {

        // 新建爬虫配置
        CrawlerConfig crawlerConfig = CrawlerConfig.me().setUrl("https://blog.csdn.net/CSDNedu/article/details/88842461")
                .addUrlXpath("(https://blog.csdn.net/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+)")
                .addXpath("title", "//h1[@class='title-article']/text()");

        // 新建爬虫
        Spider spider = Spider.create(new MyProcessor(crawlerConfig))
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://blog.csdn.net/CSDNedu/article/details/88842461")
        //开启5个线程抓取
                .thread(5);

        // 启动爬虫
        spider.start();
        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(spider.isExitWhenComplete());
    }
}
