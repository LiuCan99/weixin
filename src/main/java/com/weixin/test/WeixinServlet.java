package com.weixin.test;

import com.weixin.test.service.WeiXinService;
import com.weixin.test.util.Util;
import com.weixin.test.util.WeiXinParameterUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: liucan
 * @Date: 2020/5/5 18:05
 */
@RestController
@RequestMapping("/wx")
public class WeixinServlet extends HttpServlet {

    @GetMapping
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * signature  微信加密签名，signature结合了开发者填写的token参数和请求中的   timestamp参数、nonce参数。
         * timestamp	时间戳
         * nonce	随机数
         * echostr	随机字符串
         *
         */
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //校验请求
        if(WeiXinParameterUtil.check(timestamp,nonce,signature)){
            //若确认此次GET请求来自微信服务器，则原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
            PrintWriter writer = response.getWriter();
            //返回echostr参数
            writer.print(echostr);
            //释放资源
            writer.flush();
            writer.close();
            System.out.println("成功");
        }else {
            System.out.println("接入失败");
        }

    }


    /**
     * 接收消息和推送消息
     * @param request
     * @param response
     */
    @PostMapping
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //处理消息和消息推送(解析xml数据包)
        Map<String,String> requestMap= Util.parseRequest(request.getInputStream());
        System.out.println(requestMap);

        //回复数据
        String responseXML=WeiXinService.getResponse(requestMap);
        System.out.println(responseXML);
        PrintWriter out = response.getWriter();
        out.print(responseXML);
        out.flush();
        out.close();
    }



}
