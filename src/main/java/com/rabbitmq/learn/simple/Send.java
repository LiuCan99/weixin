package com.rabbitmq.learn.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.learn.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单队列--生产者生产消息
 * @Author: liucan
 * @Date: 2020/6/4 18:27
 */
public class Send {
    //定义queue名称
    private static final String QUEUE_NAME="test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取一个连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channe = connection.createChannel();

        //创建队列声明
        channe.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg="hello simple!";

        channe.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        System.out.println("-----send msg:"+msg);

        //关闭连接
        channe.close();
        connection.close();
    }

}
