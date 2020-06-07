package com.rabbitmq.learn.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.learn.util.ConnectionUtils;

/**
 * @Author: liucan
 * @Date: 2020/6/5 11:52
 */
public class RoutingSend {

    //交换机名称
    private static String EXCHANG_NAME="test_exchang_direct";

    public static void main(String[] args)throws  Exception {
        //获取连接
        Connection connection= ConnectionUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明交换机  direct:处理路由键
        channel.exchangeDeclare(EXCHANG_NAME,"direct");

        //发送消息
        String msg="hello direct";

        //设置路由key
        String routingKey="info";
        channel.basicPublish(EXCHANG_NAME,routingKey,null,msg.getBytes());
        System.out.println("Send:"+msg);

        channel.close();
        connection.close();

    }
}
