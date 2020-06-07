package com.weixin.test.domain.button;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liucan
 * @Date: 2020/5/7 10:03
 */
public class PhotoOrAlbumButton extends  AbstractButt {
    private String type="pic_photo_or_album";
    private String key;
    private List<AbstractButt> abstractButts=new ArrayList<>();

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

    public List<AbstractButt> getAbstractButts() {
        return abstractButts;
    }

    public void setAbstractButts(List<AbstractButt> abstractButts) {
        this.abstractButts = abstractButts;
    }

    public PhotoOrAlbumButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
