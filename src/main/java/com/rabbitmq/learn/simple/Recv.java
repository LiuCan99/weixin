package com.rabbitmq.learn.simple;

import com.rabbitmq.client.*;
import com.rabbitmq.learn.util.ConnectionUtils;

import java.io.IOException;

/**
 * 简单队列--消费者获取消息
 * @Author: liucan
 * @Date: 2020/6/4 18:43
 */
public class Recv {
    //定义queue名称
    private static final String QUEUE_NAME="test_simple_queue";

    public static void main(String[] args) throws  Exception{
        //获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        //从连接中获取一个通道
        Channel channel = connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义消费者
        DefaultConsumer consumer=new DefaultConsumer(channel){
            //获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg =new String(body,"utf-8");
                System.out.println("[Recv]:"+msg);

            }
        };

        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }

    /**
     * 老版本的获取消息
     * @throws Exception
     */
    public static void oldApi()throws  Exception{
        //获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        //从连接中获取一个通道
        Channel channel = connection.createChannel();
        //定义队列的消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msgStr=new String(delivery.getBody());
            System.out.println("[oldRecv]:"+msgStr);

        }

    }
}
