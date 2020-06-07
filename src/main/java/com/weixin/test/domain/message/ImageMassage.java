package com.weixin.test.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @Author: liucan
 * @Date: 2020/5/6 11:04
 */
@XStreamAlias("xml")
public class ImageMassage extends BaseMassage {
    @XStreamAlias("MediaId")
    private String  mediaId;

    @XStreamAlias("PicUrl")
    private String picUrl;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public ImageMassage(Map<String, String> requestMap, String mediaId,String picUrl) {
        super(requestMap);
        this.setMsgType("image");
        this.setMediaId(mediaId);
        this.picUrl=picUrl;
    }
}
