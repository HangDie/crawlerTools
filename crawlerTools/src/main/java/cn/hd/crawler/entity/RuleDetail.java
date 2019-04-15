package cn.hd.crawler.entity;

public class RuleDetail {

    private Integer detailId;
    private Integer detailRuleId;
    private String detailUrl;
    private String detailDataXpath;
    private String detailUrlXpath;
    private String detailUrlContain;
    private String detailDataPass;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getDetailRuleId() {
        return detailRuleId;
    }

    public void setDetailRuleId(Integer detailRuleId) {
        this.detailRuleId = detailRuleId;
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
