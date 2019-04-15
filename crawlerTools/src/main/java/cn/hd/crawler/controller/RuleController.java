package cn.hd.crawler.controller;

import cn.hd.crawler.service.RuleServiceImpl;
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

}
