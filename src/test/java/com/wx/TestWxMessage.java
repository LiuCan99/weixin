package com.wx;

import com.thoughtworks.xstream.XStream;
import com.weixin.test.domain.message.TextMessage;
import com.weixin.test.util.WeiXinParameterUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: liucan
 * @Date: 2020/5/6 11:32
 */

public class TestWxMessage {

    @Test
    public void testMsg(){
        HashMap map=new HashMap();
        map.put("toUserName","to");
        map.put("fromUserName","from");
        map.put("msgType","text");
        TextMessage textMessage=new TextMessage(map,"你好");
        System.out.println(textMessage);

        //转为xml对象
        XStream stream=new XStream();
        //每一个类之前都要加 @XStreamAlias("xml") 转换跟结点为<指定名称>
        stream.processAnnotations(TextMessage.class);

        String toXML = stream.toXML(textMessage);
        System.out.println(toXML);
    }

    @Test
    public void testGetToken(){
        System.out.println(WeiXinParameterUtil.getAccessToken());
     }

}
