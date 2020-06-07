package com.wx;

import com.weixin.test.service.WeiXinService;
import org.junit.Test;

/**
 * @Author: liucan
 * @Date: 2020/5/8 11:27
 */
public class TestGetUserInfo {

    @Test
    public void getUserInfo(){
        String openId="orNwy0R4FKFPoJe1w4EY4Jd-4aaU";
        String userInfo = WeiXinService.getUserInfo(openId);
        System.out.println(userInfo);
    }
}
