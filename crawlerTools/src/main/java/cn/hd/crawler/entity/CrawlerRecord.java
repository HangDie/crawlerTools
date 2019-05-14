package cn.hd.crawler.entity;

import java.util.Date;

public class CrawlerRecord {
    private Integer recordId;

    private Integer ruleId;

    private Date startTime;

    private String saveTableName;

    private Rule rule;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getSaveTableName() {
        return saveTableName;
    }

    public void setSaveTableName(String saveTableName) {
        this.saveTableName = saveTableName;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}