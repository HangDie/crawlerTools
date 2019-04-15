package cn.hd.crawler.core.model;

import java.util.*;

public class CrawlerConfig {

    // 需要抓取的数据源集合
    private Map<String, String> xpaths = new HashMap<>();
    // 需要抓取的网页入口
    private String url = "";
    // 需要持续获取的网页入口规则
    private List<String> urlsXpaths = new ArrayList<>();
    // url contain规则
    private List<String> urlsContain = new ArrayList<>();

    public static CrawlerConfig me() {
        return new CrawlerConfig();
    }

    public Map<String, String> getXpaths() {
        return xpaths;
    }

    public CrawlerConfig setXpaths(Map<String, String> xpaths) {
        this.xpaths = xpaths;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CrawlerConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<String> getUrlsXpaths() {
        return urlsXpaths;
    }

    public CrawlerConfig setUrlsXpaths(List<String> urlsXpaths) {
        this.urlsXpaths = urlsXpaths;
        return this;
    }

    public CrawlerConfig addXpath(String key, String xpath) {
        this.xpaths.put(key, xpath);
        return this;
    }

    public CrawlerConfig addUrlXpath(String xpath) {
        this.urlsXpaths.add(xpath);
        return this;
    }

    public List<String> getUrlsContain() {
        return urlsContain;
    }

    public CrawlerConfig setUrlsContain(List<String> urlsContain) {
        this.urlsContain = urlsContain;
        return this;
    }

    public CrawlerConfig addUrlContain(String urlContain) {
        this.urlsContain.add(urlContain);
        return this;
    }
    
}
