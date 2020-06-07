package com.weixin.test.service;

import com.weixin.test.domain.message.BaseMassage;
import com.weixin.test.domain.message.TextMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 处理事件推送service
 * @Author: liucan
 * @Date: 2020/5/8 15:15
 */
@Service
@Transactional
public class EventPushService {

    /**
     * 处理click类型的按钮菜单
     * @param requestMap
     * @return
     */
    public static   BaseMassage dealClick(Map<String, String> requestMap) {
        String eventKey = requestMap.get("EventKey");

        switch (eventKey){
            //点击菜单1 （创建菜单时自定义的key）
            case "1":
                //处理点击了第一个一级菜单
                return new TextMessage(requestMap,"你点击了第一个一级菜单");
            case "32":
                //处理点击了第三个一级菜单的第二个子菜单
                return new TextMessage(requestMap,"你点击了第三个一级菜单的第二个子菜单");
            default:
                break;
        }
        return null;
    }

    /**
     * 处理view类型的按钮菜单
     * @param requestMap
     * @return
     * todo
     */
    public static BaseMassage dealView(Map<String, String> requestMap) {
        return null;
    }
}
