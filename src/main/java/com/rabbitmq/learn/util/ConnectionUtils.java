package com.rabbitmq.learn.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: liucan
 * @Date: 2020/6/4 17:58
 */
public class ConnectionUtils  {

    /**
     * 获取MQ连接
     * @return
     */
    public  static Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //AMQP 5672
        factory.setPort(5672);
        //vhost
        factory.setVirtualHost("/vhost_lc");
        //用户名
        factory.setUsername("user_lc");
        //密码
        factory.setPassword("liucan");

        return factory.newConnection();
    }


}
