package com.weixin.test.domain.template;

/**
 * 微信模板详细内容
 * @Author: liucan
 * @Date: 2020/5/8 17:39
 */
public class TemplateData {

    private String value;
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TemplateData() {
    }

    public TemplateData(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
