package com.weixin.test.util;

import com.weixin.test.domain.message.AccessToken;
import net.sf.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 获得微信公众号参数
 * @Author: liucan
 * @Date: 2020/5/8 14:47
 */
public class WeiXinParameterUtil {

    /**
     * 微信公众号配置信息
     */
    //自己配置的签名
    private static  final  String TOKEN="abc";
    //微信公众号 测试号信息
    public static final  String APPID="wx7e7b66520fc3faa0";
    public static final  String APPSECRET="32765025e426528b74fb5718f1409ab9";
    //获取access_token的接口路径
    public static  final String GET_TOKEN_URL=" https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //用于存储token
    public static AccessToken at;


    /**
     * 校验请求是否来自该微信微信服务器
     * 验证签名
     *  开发者通过检验signature对请求进行校验（下面有校验方式）。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，
     * 否则接入失败。加密/校验流程如下：
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @return
     */
    public static boolean check(String timestamp, String nonce, String signature) {

        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs=new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str=strs[0]+strs[1]+strs[2];
        String mySig=sha1(str);

        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return mySig.equalsIgnoreCase(signature);
    }

    /**
     * 使用sha1加密
     * @param str
     * @return
     */
    private static String sha1(String str) {
        try {
            //获得一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(str.getBytes());
            char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder stringBuilder=new StringBuilder();
            //处理加密结果
            for(byte b:digest){
                stringBuilder.append(chars[(b>>4)&15]);
                stringBuilder.append(chars[b&15]);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *获取token
     */
    public static  void   getToken(){
        //替换信息
        String url=GET_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        String tokenStr = Util.get(url);
        JSONObject jsonObj = JSONObject.fromObject(tokenStr);
        String token = jsonObj.getString("access_token");
        String expireIn = jsonObj.getString("expires_in");

        //创建token对象，并存储
        AccessToken accessToken=new AccessToken(token,expireIn);
        at=accessToken;
    }


    /**
     * 向外暴露的获取token的方法
     * @return
     */
    public static  String getAccessToken(){
        if(at==null||at.isExpired()){
            getToken();
        }
        return at.getAccessToken();
    }

}
