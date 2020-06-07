package com.weixin.test.domain.button;

/**
 * @Author: liucan
 * @Date: 2020/5/7 9:48
 */
public class ClickButton extends AbstractButt {

    private String type="click";
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ClickButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
