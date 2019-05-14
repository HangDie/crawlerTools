package cn.hd.crawler.service;

import cn.hd.crawler.dao.RuleMapper;
import cn.hd.crawler.entity.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RuleServiceImpl {

    @Autowired
    private RuleMapper ruleDao;

    public List<Rule> queryAll() {
        return ruleDao.queryAll();
    }

    public Rule queryRuleById(Integer ruleId) {
        return ruleDao.queryRuleById(ruleId);
    }

    public void add(Rule rule) {
        rule.setRuleCreateTime(new Date());
        ruleDao.insertSelective(rule);
    }

    public void update(Rule rule) {
        ruleDao.updateByPrimaryKeySelective(rule);
    }

}
