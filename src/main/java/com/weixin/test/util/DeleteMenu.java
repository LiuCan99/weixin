package com.weixin.test.util;

/**
 * 删除菜单
 * @Author: liucan
 * @Date: 2020/5/7 10:43
 */
public class DeleteMenu {
    public static void main(String[] args) {
        //准备url
        String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
        url=url.replace("ACCESS_TOKEN", WeiXinParameterUtil.getAccessToken());
        //发送请求
        String result = Util.get(url).toString();
        System.out.println(result);
    }
}
