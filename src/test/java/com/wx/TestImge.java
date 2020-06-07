package com.wx;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * 测试图片识别
 * @Author: liucan
 * @Date: 2020/5/7 14:27
 */
public class TestImge {

    //设置APPID/AK/SK
    public static final String APP_ID = "19758563";
    public static final String API_KEY = "Mj2xOrPBxdRLXMQUnVcISiH3";
    public static final String SECRET_KEY = "37N8yEDI3TVbTz0sz2s6ealffFz7KScz";

    @Test
    public void testImge() throws JSONException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:/Users/lenovo/Desktop/test.png";
        //本地图片 解析接口
        JSONObject jsonObject = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(jsonObject.toString(2));
    }
}
