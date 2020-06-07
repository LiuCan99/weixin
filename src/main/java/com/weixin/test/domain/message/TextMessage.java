package com.weixin.test.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 文本消息
 * @Author: liucan
 * @Date: 2020/5/6 10:37
 */
@XStreamAlias("xml")
public class TextMessage  extends  BaseMassage{

    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //设置文本消息的消息类型为text
    public TextMessage(Map<String,String> requestMap,String content) {
        super(requestMap);
        this.setMsgType("text");
        this.content=content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
