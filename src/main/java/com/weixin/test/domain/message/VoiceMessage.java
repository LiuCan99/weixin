package com.weixin.test.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.weixin.test.domain.message.BaseMassage;

import java.util.Map;

/**
 * @Author: liucan
 * @Date: 2020/5/6 11:08
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMassage {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public VoiceMessage(Map<String, String> requestMap,String mediaId) {
        super(requestMap);
        this.setMsgType("voic");
        this.mediaId=mediaId;

    }
}
