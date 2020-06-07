package com.wx;

import com.weixin.test.service.WeiXinService;
import org.junit.Test;

/**
 * 二维码
 * @Author: liucan
 * @Date: 2020/5/8 10:33
 */
public class TestQrCode {

    /**
     * 生成临时二维码
     * 获取生成的二维码： https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     */
    @Test
    public void GetOrCodeTicket(){
        String orCodeTicket = WeiXinService.getOrCodeTicket();
        System.out.println(orCodeTicket);
    }
}
