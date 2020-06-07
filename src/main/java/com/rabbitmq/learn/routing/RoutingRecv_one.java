package com.rabbitmq.learn.routing;

import com.rabbitmq.client.*;
import com.rabbitmq.learn.util.ConnectionUtils;

import java.io.IOException;

/**
 * @Author: liucan
 * @Date: 2020/6/5 15:56
 */
public class RoutingRecv_one {

    //队列名称
    private static String QUEUE_NAME="test_queue_fanout_one";
    //交换机名称
    private static String EXCHANG_NAME="test_exchang_direct";

    public static void main(String[] args)throws Exception {
        //获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.basicQos(1);
        //设置路由key
        String routingKey="error";
        //绑定队列到交换机（转发器）
        channel.queueBind(QUEUE_NAME,EXCHANG_NAME,routingKey);

        //定义消费者
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msgStr=new String(body,"utf-8");
                System.out.println("[RoutingRecv_one]："+msgStr);

            }
        };

        //应答
        Boolean autoAck=false;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);


    }
}
