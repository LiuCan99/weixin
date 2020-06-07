package com.weixin.test.domain.button;

import com.weixin.test.domain.button.AbstractButt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liucan
 * @Date: 2020/5/7 9:36
 */
public class Button {

    private List<AbstractButt> button= new ArrayList();

    public List<AbstractButt> getButton() {
        return button;
    }

    public void setButton(List<AbstractButt> button) {
        this.button = button;
    }

    public Button() {
    }

    public Button(List<AbstractButt> button) {
        this.button = button;
    }
}
