package com.weixin.test.service;

import com.weixin.test.domain.message.*;
import com.weixin.test.domain.template.WxTemplate;
import com.weixin.test.util.*;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

/**
 * @Author: liucan
 * @Date: 2020/5/5 18:49
 */
public class WeiXinService {

    @Autowired
    private MessageHandleService messageHandleService;
    @Autowired
    private EventPushService eventPushService;


    /**
     * 用于处理所有的消息和事件的回复
     * @param requestMap
     * @return 返回的是xml数据包
     */
    public static String getResponse(Map<String, String> requestMap) {

        BaseMassage baseMassage=null;
        //获得消息类型
        String msgType = requestMap.get("MsgType");

        //根据消息类型进行消息处理
        switch (msgType){
            case "text":
                //文本为text
                baseMassage=MessageHandleService.dealTextMessage(requestMap);

                break;
            case "image":
                //图片为image(识别图片中的文字)
                baseMassage= MessageHandleService.dealImageMessage(requestMap);

                break;
            case "voice":
                //语音为voice


                break;
            case "video":
                //视频为video


                break;
            case "shortvideo":
                //小视频为shortvideo


                break;
            case "location":
                //地理位置为location


                break;

            case "link":
                //链接为link


                break;

            case "event":
                baseMassage=dealEvent(requestMap);


                break;
            default:

                break;
        }

        //把消息对象处理为xml数据包
        if(baseMassage!=null){
            return Util.beanToXml(baseMassage);
        }
       return null;
    }


    /**
     * 处理事件推送
     * @param requestMap
     * @return
     */
    public static   BaseMassage dealEvent(Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        switch (event){
            case "CLICK":
                return EventPushService.dealClick(requestMap);
            case "VIEW":
                return EventPushService.dealView(requestMap);
            default:
                break;
        }

        return null;
    }


    /**
     * 上传临时素材（保存时间为3天）
     * @param path  上传的文件路径
     * @param type  上传的文件类型
     * @return
     */
    public  static   String uploadFile(String path,String type){

        File file=new File(path);
        //地址
        String url="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

            url=url.replace("ACCESS_TOKEN", WeiXinParameterUtil.getAccessToken()).replace("TYPE",type);
        try {
            URL urlObj=new URL(url);
            //强转为安全链接  https请求
            HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
            //设置连接的信息（允许进行输入、输出）
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //不使用缓存
            connection.setUseCaches(false);
            //设置请求头信息
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Charset","utf8");
            //数据边界
            String boundary="-----"+System.currentTimeMillis();
            connection.setRequestProperty("Content-Type","multipart/form-data;boundary"+boundary);
            //获取输出\输出流
            OutputStream outputStream = connection.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file);
            //第一部分：头部信息
            //准备头部信息
            StringBuilder sb=new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+file.getName()+"\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            outputStream.write(sb.toString().getBytes());
            System.out.println(sb.toString());

            //第二部分：文件内容
            byte[] bytes = new byte[1024];
            int len;
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            //第三部分：尾部信息
            String foo="\r\n--"+boundary+"--\r\n";
            outputStream.write(foo.getBytes());
            outputStream.flush();
            outputStream.close();

            //读取返回数据
            InputStream inputStream = connection.getInputStream();
            StringBuilder resp=new StringBuilder();
            while ((len=inputStream.read(bytes))!=-1){
                resp.append(new String(bytes,0,len));
            }

            return resp.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取带参数的二维码
     * @return
     */
    public static String getOrCodeTicket(){

        String accessToken = WeiXinParameterUtil.getAccessToken();

        String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
        //生成临时字符串二维码
        String data="{\"expire_seconds\": 600, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";

        String result = Util.post(url, data);
        System.out.println(result);
        String ticke = JSONObject.fromObject(result).getString("ticket");


        System.out.println(ticke);
        return ticke;
    }


    /**
     * 获取用户的基本信息
     * @return
     */
    public static String getUserInfo(String openId){
        String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url=url.replace("ACCESS_TOKEN",WeiXinParameterUtil.getAccessToken())
                .replace("OPENID",openId);
        String result = Util.get(url);
        return result;

    }

    /**
     * 发送模板信息
     * @param template
     */
    public static void sendTemplateMessage(WxTemplate template){
        //获得accessToken
        String accessToken = WeiXinParameterUtil.getAccessToken();
        String url ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        //准备数据
        JSONObject jsonObject = JSONObject.fromObject(template);
        String data = jsonObject.toString();
        //发送请求
        String post = Util.post(url, data);

    }




}
