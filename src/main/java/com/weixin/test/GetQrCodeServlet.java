package com.weixin.test;

import com.weixin.test.service.WeiXinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 获取二维码
 * @Author: liucan
 * @Date: 2020/5/8 10:56
 */
@RestController
@RequestMapping("/ServletGetQrCode")
public class GetQrCodeServlet extends HttpServlet {
    @Override
    @PostMapping
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    @GetMapping
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String ticket = WeiXinService.getOrCodeTicket();
        writer.print(ticket);
        writer.flush();
        writer.close();
    }
}
