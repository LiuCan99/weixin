package com.weixin.test.service;

import com.baidu.aip.ocr.AipOcr;
import com.weixin.test.domain.User;
import com.weixin.test.domain.message.Article;
import com.weixin.test.domain.message.BaseMassage;
import com.weixin.test.domain.message.NewsMessage;
import com.weixin.test.domain.message.TextMessage;
import com.weixin.test.util.TulingApiUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用于处理所有的消息和事件的回复service
 * @Author: liucan
 * @Date: 2020/5/8 15:09
 */
@Service
@Transactional
public class MessageHandleService {

    @Resource
    private UserService userService;

    /**
     *  百度ai图片识别文字  设置APPID/AK/SK
     */
    public static final String APP_ID = "19758563";
    public static final String API_KEY = "Mj2xOrPBxdRLXMQUnVcISiH3";
    public static final String SECRET_KEY = "37N8yEDI3TVbTz0sz2s6ealffFz7KScz";


    /**
     * 处理文本消息
     * @param requestMap
     * @return
     */
    public static BaseMassage dealTextMessage(Map<String, String> requestMap) {
        //用户发来的内容
        String content = requestMap.get("Content");
        //需要处理的文本消息
        switch (content){
            case "图文":
                List<Article> articles=new ArrayList<>();
                articles.add(new Article("这是图文消息标题","这是图文消息的详细介绍","http://mmbiz.qpic.cn/mmbiz_jpg/Z6IIso2icicVzIDBPibSgkEOMAPoreahWC7tKw3nELCIhXf7t1JVz1tkZywSXmSrNI3axG3EwWPNyvJu4T39YDEEw/0","http://www.baidu.com"));
                NewsMessage newsMessage=new NewsMessage(requestMap,articles);
                return newsMessage;

            default:
                break;
        }
        //调用图灵机器人
        String result  = TulingApiUtil.getTulingResult(content);
        TextMessage textMessage=new TextMessage(requestMap,result);
        return textMessage;
    }



    /**
     * 进行图片识别 （百度AI  ai.haidu.com）
     * @param requestMap
     * @return
     */
    public static   BaseMassage dealImageMessage(Map<String, String> requestMap) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        //用户发送过来的图片
        String path = requestMap.get("PicUrl");

        //网络图片  解析接口
        org.json.JSONObject res = client.basicGeneralUrl(path, new HashMap<String, String>());

        //转为jsonObjec
        String json = res.toString();
        JSONObject jsonObject = JSONObject.fromObject(json);

        //获得解析到的文字信息（是一个集合）
        JSONArray jsonArray = jsonObject.getJSONArray("words_result");
        Iterator<JSONObject> iterator = jsonArray.iterator();

        //返回解析到的文字
        StringBuilder sb=new StringBuilder();
        while (iterator.hasNext()){
            JSONObject next = iterator.next();
            sb.append(next.get("words"));
        }
        return new TextMessage(requestMap,sb.toString());
    }
}
