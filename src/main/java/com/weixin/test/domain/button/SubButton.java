package com.weixin.test.domain.button;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liucan
 * @Date: 2020/5/7 9:56
 */
public class SubButton extends AbstractButt {

    private List<AbstractButt> sub_button=new ArrayList<>();

    public List<AbstractButt> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButt> sub_button) {
        this.sub_button = sub_button;
    }

    public SubButton(String name) {
        super(name);
    }
}
