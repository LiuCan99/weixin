package com.weixin.test.util;

import com.weixin.test.domain.template.IndustryInfo;
import net.sf.json.JSONObject;

/**
 * 行业信息设置，每月一次
 * 设置行业信息：
 * https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN
 *
 * 获取行业信息：
 * https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN
 *
 * @Author: liucan
 * @Date: 2020/5/7 15:41
 */
public class IndustryInfoUtil {

    /**
     * 设置行业
     */
    public void setIndustryInfo(){
        String accessToken = WeiXinParameterUtil.getAccessToken();
        //接口地址
        String url="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+accessToken;
        //数据
        IndustryInfo industryInfo=new IndustryInfo("1","2");
        JSONObject jsonObject = JSONObject.fromObject(industryInfo);
        String data = jsonObject.toString();

        //发送请求
        String post = Util.post(url, data);
        System.out.println(post);
    }

    /**
     * 获取行业（）
     */
    public static void getIndustryInfo(){
        String accessToken = WeiXinParameterUtil.getAccessToken();
        //接口地址
        String url="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+accessToken;
        String s = Util.get(url);
        System.out.println(s);
    }
}
