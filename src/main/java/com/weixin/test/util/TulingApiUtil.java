package com.weixin.test.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * 图灵机器人
 * @Author: liucan
 * @Date: 2020/5/10 13:10
 */
public class TulingApiUtil {

    private static final String APPKEY="aa634d02e7c4435dafc6cf53b2a7f039";
    /**
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
     * @param content
     * @return
     */
    public static String getTulingResult(String content){
       //此处为图灵api接口，参数key需要自己去注册申请
        String apiUrl = "http://www.tuling123.com/openapi/api?key=APPKEY&info=";
        apiUrl=apiUrl.replace("APPKEY",APPKEY);

        String param = "";
        try {
            param = apiUrl+ URLEncoder.encode(content,"utf-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //将参数转为url编码

        /** 发送httpget请求 */
        HttpGet request = new HttpGet(param);
        String result = "";
        try {
            HttpResponse response = HttpClients.createDefault().execute(request);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** 请求失败处理 */
        if(null==result){
            return "对不起，你说的话真是太高深了……";
        }

        try {
            JSONObject json = new JSONObject(result);
            //以code=100000为例，参考图灵机器人api文档
            if(100000==json.getInt("code")){
                result = json.getString("text");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

}
