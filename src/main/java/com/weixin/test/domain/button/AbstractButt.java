package com.weixin.test.domain.button;

/**
 * @Author: liucan
 * @Date: 2020/5/7 9:40
 */
public abstract class AbstractButt {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractButt() {
    }

    public AbstractButt(String name) {
        this.name = name;
    }
}
