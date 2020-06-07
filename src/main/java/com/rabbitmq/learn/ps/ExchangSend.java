package com.rabbitmq.learn.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.learn.util.ConnectionUtils;

/**
 * @Author: liucan
 * @Date: 2020/6/5 11:52
 */
public class ExchangSend {

    //交换机名称
    private static String EXCHANG_NAME="test_exchang_fanout";

    public static void main(String[] args)throws  Exception {
        //获取连接
        Connection connection= ConnectionUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明交换机  fanout：分发
        channel.exchangeDeclare(EXCHANG_NAME,"fanout");

        //发送消息
        String msg="hello ps";

        channel.basicPublish(EXCHANG_NAME,"",null,msg.getBytes());
        System.out.println("Send:"+msg);

        channel.close();
        connection.close();

    }
}
