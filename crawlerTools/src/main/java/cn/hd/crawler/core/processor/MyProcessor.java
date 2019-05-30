package cn.hd.crawler.core.processor;

import cn.hd.crawler.core.model.CrawlerConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
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
        boolean go = false;
        // 定义如何抽取页面信息，并保存下来
        for(String contain : crawlerConfig.getUrlsContain()) {
            if (page.getUrl().regex(contain).match()) {
                go = true;
                break;
            }
        }
        if(go){
            for (Map.Entry<String, String> entry : crawlerConfig.getXpaths().entrySet()) {
                page.putField(entry.getKey(), page.getHtml().xpath(entry.getValue()).toString());
            }
        }
        // 从页面发现后续的url地址来抓取
        for (String xpath : crawlerConfig.getUrlsXpaths()) {
            System.out.println(page.getHtml().xpath(xpath));
            page.addTargetRequests(page.getHtml().xpath(xpath).links().all());
        }

        System.out.println(page.getUrl().toString());
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
        CrawlerConfig crawlerConfig = CrawlerConfig.me().setUrl("http://rsc.jmu.edu.cn/")
                .addUrlXpath("//a[@class='newsTi']")
                .addUrlContain("([\\w\\W]*)")
                .addXpath("标题", "//div[@id='main']/h1/html()")
                .addXpath("全文内容", "//div[@id='main']/html()");

        // 新建爬虫
        Spider spider = Spider.create(new MyProcessor(crawlerConfig))
                .addUrl("http://rsc.jmu.edu.cn/")
        //开启5个线程抓取
                .thread(5);

        // 启动爬虫
        spider.start();

    }
}
