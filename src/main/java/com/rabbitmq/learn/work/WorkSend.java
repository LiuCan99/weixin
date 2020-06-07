package com.rabbitmq.learn.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.learn.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列--生产消息
 * 轮询分发：不管谁忙还是谁清闲，分发的消息数量都是一样的
 * 公平分发（fail dipatch）：
 * @Author: liucan
 * @Date: 2020/6/5 10:13
 */
public class WorkSend {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获得队列名
        String queueName = WorkEnum.QUEUE_NAME.getKey();
        //获取连接
        Connection connection= ConnectionUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(queueName,false,false,false,null);

        /**
         *每个消费者，发送确认消息之前，消息队列不发送下一个消息到消费者，一次只能处理一个消息
         * 限制发送给同一个消费者 不得超过一个消息
         *
         * */
        int prefethCount=1;
        channel.basicQos(prefethCount);

        //生产消息
        for(int i=0;i<50;i++){
            String msg="workQuese:"+i;
            channel.basicPublish("",queueName,null,msg.getBytes());
            System.out.println(msg);
            Thread.sleep(i*10);

        }

        //关闭连接
        channel.close();
        connection.close();

    }

}
