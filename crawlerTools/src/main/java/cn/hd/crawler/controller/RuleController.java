package cn.hd.crawler.controller;

import cn.hd.crawler.entity.CrawlerRecord;
import cn.hd.crawler.entity.Rule;
import cn.hd.crawler.service.CrawlerRecordServiceImpl;
import cn.hd.crawler.service.RuleServiceImpl;
import cn.hd.crawler.utils.ObjectMapperUtil;
import cn.hd.crawler.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleServiceImpl ruleService;
    @Autowired
    private CrawlerRecordServiceImpl crawlerRecordService;

    @RequestMapping("/queryRules")
    @ResponseBody
    public ServerResponse queryRules() {
        return ServerResponse.createBySuccess(ruleService.queryAll());
    }

    @RequestMapping("/queryRuleById")
    @ResponseBody
    public ServerResponse queryRuleById(@RequestBody Map<String, Object> params) {
        Integer ruleId;
        try {
            ruleId = (Integer) params.get("ruleId");
        } catch (Exception e) {
            ruleId = 1;
            e.printStackTrace();
        }
        return ServerResponse.createBySuccess(ruleService.queryRuleById(ruleId));
    }

    @RequestMapping("/queryRecordById")
    @ResponseBody
    public ServerResponse queryRecordById(@RequestBody Map<String, Object> params) {
        Integer recordId;
        Integer ruleId;
        try {
            recordId = (Integer) params.get("recordId");
            CrawlerRecord crawlerRecord = crawlerRecordService.queryByRecordId(recordId);
            ruleId = crawlerRecord.getRuleId();
        } catch (Exception e) {
            ruleId = 1;
            e.printStackTrace();
        }
        return ServerResponse.createBySuccess(ruleService.queryRuleById(ruleId));
    }

    @RequestMapping("/save")
    @ResponseBody
    public ServerResponse save(@RequestBody Map<String, Object> params){
        Rule rule = new Rule();
        try {
            if(params.get("ruleId") != null) {
                rule.setRuleId(Integer.parseInt(params.get("ruleId").toString()));
            }
            rule.setRuleName(params.get("ruleName").toString());
            rule.setDetailUrl(params.get("detailUrl").toString());
            rule.setDetailDataXpath(params.get("detailDataXpath").toString());
            rule.setDetailUrlXpath(params.get("detailUrlXpath").toString());
            rule.setDetailUrlContain(params.get("detailUrlContains").toString());
            if(rule.getRuleId() == null){
                ruleService.add(rule);
                return ServerResponse.createBySuccess("Success");
            }
            else{
                ruleService.update(rule);
                return ServerResponse.createBySuccess("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByFailure("Error");
        }
    }

    @RequestMapping("/go")
    @ResponseBody
    public ServerResponse go(@RequestBody Map<String, Object> params){
        Integer ruleId = 0;
        try {
            if(params.get("ruleId") != null) {
                ruleId = Integer.parseInt(params.get("ruleId").toString());
                crawlerRecordService.goCrawler(ruleId);
            }
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByFailure("Error");
        }
    }
}
