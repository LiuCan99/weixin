package com.weixin.test.domain.template;

/**
 * 行业信息
 * @Author: liucan
 * @Date: 2020/5/7 15:38
 */
public class IndustryInfo {
    //公众号模板消息所属行业编号
    private String industry_id1;

    //公众号模板消息所属行业编号
    private String industry_id2;

    public String getIndustry_id1() {
        return industry_id1;
    }

    public void setIndustry_id1(String industry_id1) {
        this.industry_id1 = industry_id1;
    }

    public String getIndustry_id2() {
        return industry_id2;
    }

    public void setIndustry_id2(String industry_id2) {
        this.industry_id2 = industry_id2;
    }

    public IndustryInfo() {
    }

    public IndustryInfo(String industry_id1, String industry_id2) {
        this.industry_id1 = industry_id1;
        this.industry_id2 = industry_id2;
    }
}
