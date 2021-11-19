package com.honger1234.rabbitmqexample.one;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者：发消息
 */
public class producer {
    //队列名称
    public static final String QUEUE_NAME="hello";

    //发消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //c创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //工厂IP，链接rabbitmq队列
        connectionFactory.setHost("192.168.16.129");
        //用户名
        connectionFactory.setUsername("zt");
        //密码
        connectionFactory.setPassword("password");
        //创建链接
        Connection connection = connectionFactory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        /**
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.是否自动删除 最后一个消费者短开连接以后 该队列是否自动删除 true 自动删除
         * 4.该队列是否只供一个消费者进行消费 是否进行共享 true 可以多个消费者消费
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message="hello world";

        /**
         * 发送一个消息
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");

    }
}
