package com.weixin.test.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @Author: liucan
 * @Date: 2020/5/6 11:18
 */
@XStreamAlias("xml")
public class MusicMessage extends  BaseMassage {
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public MusicMessage(Map<String, String> requestMap,Music music) {
        super(requestMap);
        this.setMsgType("music");
        this.music=music;

    }
}
