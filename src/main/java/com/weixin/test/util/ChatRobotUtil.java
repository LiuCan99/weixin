package com.weixin.test.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 聊天机器人
 * @Author: liucan
 * @Date: 2020/5/8 14:57
 */
public class ChatRobotUtil {

    /**
     * 聊天机器人的AppKey aa634d02e7c4435dafc6cf53b2a7f039
     * 聚合数据
     */
//    private static final String APPKEY="06746df3e824975f5317eeacb40af434";
    private static final String APPKEY="aa634d02e7c4435dafc6cf53b2a7f039";

    /**
     * 调用图灵机器人聊天    1bd243ae85eaa5a63d8b25b0ba3c1846
     * @param content  发送消息
     * @return
     */
    public static String chat(String content) {
        String result =null;
        String url ="http://op.juhe.cn/robot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info",content);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
        params.put("loc","");//地点，如北京中关村
        params.put("lon","");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat","");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联

        try {
            result = Util.net(url, params, "GET");
            System.out.println(result);

            //解析json
            JSONObject object = JSONObject.fromObject(result);

            //判断是否运行正常
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));

                return  object.getJSONObject("result").getString("text");
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
                return  null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
