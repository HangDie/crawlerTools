package cn.hd.crawler.service;

import cn.hd.crawler.core.model.CrawlerConfig;
import cn.hd.crawler.core.pipeline.MyPipeline;
import cn.hd.crawler.core.processor.MyProcessor;
import cn.hd.crawler.dao.CrawlerRecordMapper;
import cn.hd.crawler.dao.ResultMapper;
import cn.hd.crawler.dao.RuleMapper;
import cn.hd.crawler.entity.CrawlerRecord;
import cn.hd.crawler.entity.Rule;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.Date;
import java.util.List;

@Service
public class CrawlerRecordServiceImpl {

    @Autowired
    CrawlerRecordMapper crawlerRecordDao;
    @Autowired
    RuleMapper ruleDao;
    @Autowired
    ResultMapper resultMapper;

    public List<CrawlerRecord> queryAll() {
        List<CrawlerRecord> records = crawlerRecordDao.queryAll();
        records.forEach(o->{
            o.setRule(ruleDao.queryRuleById(o.getRuleId()));
        });
        return records;
    }

    public CrawlerRecord queryByRecordId(Integer recordId) { return crawlerRecordDao.queryRuleById(recordId); }

    public void goCrawler(Integer ruleId) {
        Rule rule = ruleDao.selectByPrimaryKey(ruleId);
        CrawlerConfig config = CrawlerConfig.me();
        // 入口地址
        config.setUrl(rule.getDetailUrl());
        // data xpath
        JSONArray ja = JSONArray.parseArray(rule.getDetailDataXpath());
        for(int i=0;i<ja.size();i++) {
            JSONObject jo = ja.getJSONObject(i);
            System.out.println(jo.get("key").toString() + ":" + jo.get("value").toString());
            config.addXpath(jo.get("key").toString(), jo.get("value").toString());
        }
        // url xpath
        JSONArray urlXpaths = JSONArray.parseArray(rule.getDetailUrlXpath());
        for(int i=0;i<urlXpaths.size();i++) {
            config.addUrlXpath(urlXpaths.get(i).toString());
        }
        // url contain
        JSONArray urlContains = JSONArray.parseArray(rule.getDetailUrlContain());
        for(int i=0;i<urlContains.size();i++) {
            config.addUrlContain(urlContains.get(i).toString());
        }
        // 插入记录
        String saveName = String.valueOf(new Date().getTime());
        CrawlerRecord crawlerRecord = new CrawlerRecord();
        crawlerRecord.setRuleId(ruleId);
        crawlerRecord.setSaveTableName(saveName + "_" + rule.getRuleName());
        crawlerRecord.setStartTime(new Date());
        crawlerRecordDao.insertSelective(crawlerRecord);
        // crawler开始
        Spider spider = Spider.create(new MyProcessor(config)).addUrl(config.getUrl()).addPipeline(new MyPipeline().setSaveName(saveName).setMapper(resultMapper)).thread(5);
        spider.start();
    }

}
