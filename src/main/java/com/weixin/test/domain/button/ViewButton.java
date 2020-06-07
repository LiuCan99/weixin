package com.weixin.test.domain.button;

/**
 * @Author: liucan
 * @Date: 2020/5/7 9:54
 */
public class ViewButton extends AbstractButt {
    private String type="view";

    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ViewButton(String name, String url) {
        super(name);
        this.url = url;
    }
}
