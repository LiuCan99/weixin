package com.rabbitmq.learn.work;

import com.rabbitmq.client.*;
import com.rabbitmq.learn.util.ConnectionUtils;

import java.io.IOException;

/**
 * @Author: liucan
 * @Date: 2020/6/5 10:26
 */
public class WorkRecv_two {

    public static void main(String[] args)throws  Exception {
        //获得队列名
        String queueName = WorkEnum.QUEUE_NAME.getKey();
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取channle
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(queueName,false,false,false,null);
        //保证一次只分发一个
        int prefethCount=1;
        channel.basicQos(prefethCount);

        //定义消费者
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                String msgStr=new String(body,"utf-8");
                System.out.println("[WorkRecv_two]："+msgStr);

                //睡眠两秒
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[WorkRecv_two]......done");
                    channel.basicAck(envelope.getDeliveryTag(),false );
                }
            }
        };
        Boolean autoAck=false;
        channel.basicConsume(queueName,autoAck,consumer);

    }
}
