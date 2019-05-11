package cn.hd.crawler.service;

import cn.hd.crawler.dao.CrawlerRecordMapper;
import cn.hd.crawler.dao.RuleMapper;
import cn.hd.crawler.entity.CrawlerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlerRecordServiceImpl {

    @Autowired
    CrawlerRecordMapper crawlerRecordDao;
    @Autowired
    RuleMapper ruleDao;

    public List<CrawlerRecord> queryAll() {
        List<CrawlerRecord> records = crawlerRecordDao.queryAll();
        records.forEach(o->{
            o.setRule(ruleDao.queryRuleById(o.getRuleId()));
        });
        return records;
    }

    public CrawlerRecord queryByRecordId(Integer recordId) { return crawlerRecordDao.queryRuleById(recordId); }

}
