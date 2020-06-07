package com.weixin.test.util;


import com.thoughtworks.xstream.XStream;
import com.weixin.test.domain.message.*;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *问答机器人调用示例代码 － 聚合数据
 *在线接口文档：http://www.juhe.cn/docs/112
 **/

public class Util {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="*************************";

    //1.问答
    public static void getRequest1(){
        String result =null;
        String url ="http://op.juhe.cn/robot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info","");//要发送给机器人的内容，不要超过30个字符
        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
        params.put("loc","");//地点，如北京中关村
        params.put("lon","");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat","");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.数据类型
    public static void getRequest2(){
        String result =null;
        String url ="http://op.juhe.cn/robot/code";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("dtype","");//返回的数据格式，json或xml，默认json
        params.put("key",APPKEY);//您申请本接口的APPKEY，请在应用详细页查询

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *图灵机器人
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 像指定地址发送post请求，并携带data数据
     * @return
     */
    public static  String post(String url,String data){
        try {
            URL urlObj=new URL(url);
            URLConnection connection = urlObj.openConnection();
            //要发送数据需要设置为，可发送数据状态（默认为false）
            connection.setDoOutput(true);
            //获取输出流
            OutputStream outputStream = connection.getOutputStream();
            //写出数据
            outputStream.write(data.getBytes());
            //关闭流
            outputStream.close();
            //获得输入流，读取数据
            InputStream is = connection.getInputStream();
            byte[] b=new byte[1024];
            int len;
            StringBuilder sb=new StringBuilder();
            while ((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 发送get请求
     * @param url
     * @return
     */

    public static  String get(String url){
        try {
            URL urlObj=new URL(url);
            //开连接
            URLConnection connection=urlObj.openConnection();
            InputStream is = connection.getInputStream();
            byte[] b=new byte[1024];
            int len;
            StringBuilder sb=new StringBuilder();
            while ((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 解析xml数据包
     * @param is
     * @return
     */
    public static Map<String, String> parseRequest(InputStream is) {
        HashMap<String,String>map=new HashMap<>();
        SAXReader saxReader=new SAXReader();
        try {
            //读取输入流，获取文档对象
            Document document = saxReader.read(is);
            //根据文档对象，获得根结点
            Element rootElement = document.getRootElement();
            //获取根结点所有的子节点
            List<Element> elements = rootElement.elements();
            for(Element element:elements){
                map.put(element.getName(),element.getStringValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 把消息对象处理为xml数据包
     * @param baseMassage
     * @return
     */
    public static String beanToXml(BaseMassage baseMassage) {

        XStream stream=new XStream();
        //每一个类之前都要加 @XStreamAlias("xml") 转换跟结点为<指定名称>
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMassage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(VideoMessage.class);

        //设置需要处理  @XStreamAlias("xml")注释的类
        String toXML = stream.toXML(baseMassage);
        return toXML;
    }




}