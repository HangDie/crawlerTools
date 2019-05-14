package cn.hd.crawler.entity;

import java.util.Date;

public class Rule {
    private Integer ruleId;

    private String ruleName;

    private Date ruleCreateTime;

    private Integer useTimes;

    private String detailUrl;

    private String detailDataXpath;

    private String detailUrlXpath;

    private String detailUrlContain;

    private String detailDataPass;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Date getRuleCreateTime() {
        return ruleCreateTime;
    }

    public void setRuleCreateTime(Date ruleCreateTime) {
        this.ruleCreateTime = ruleCreateTime;
    }

    public Integer getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Integer useTimes) {
        this.useTimes = useTimes;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getDetailDataXpath() {
        return detailDataXpath;
    }

    public void setDetailDataXpath(String detailDataXpath) {
        this.detailDataXpath = detailDataXpath;
    }

    public String getDetailUrlXpath() {
        return detailUrlXpath;
    }

    public void setDetailUrlXpath(String detailUrlXpath) {
        this.detailUrlXpath = detailUrlXpath;
    }

    public String getDetailUrlContain() {
        return detailUrlContain;
    }

    public void setDetailUrlContain(String detailUrlContain) {
        this.detailUrlContain = detailUrlContain;
    }

    public String getDetailDataPass() {
        return detailDataPass;
    }

    public void setDetailDataPass(String detailDataPass) {
        this.detailDataPass = detailDataPass;
    }
}