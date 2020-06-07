package com.wx;

import com.weixin.test.domain.template.IndustryInfo;
import com.weixin.test.util.Util;
import com.weixin.test.util.WeiXinParameterUtil;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * 行业信息的设置与获取
 * @Author: liucan
 * @Date: 2020/5/7 15:28
 */
public class TestIndustryInfo {

    /**
     * 设置行业
     */
    @Test
    public void setIndustryInfo(){
        String accessToken =WeiXinParameterUtil.getAccessToken();
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
     * 获取行业
     */
    @Test
    public void getIndustryInfo(){
        String accessToken = WeiXinParameterUtil.getAccessToken();
        //接口地址
        String url="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+accessToken;
        String s = Util.get(url);
        System.out.println(s);
    }
}
