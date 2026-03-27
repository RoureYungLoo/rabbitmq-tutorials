//package com.itheima.publisher;
//
////import com.itheima.common.rabbitmq.client.RabbitClient;
//
//import com.itheima.common.rabbitmq.client.RabbitClient;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageBuilder;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
//import javax.annotation.Resource;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author Mr.M
// * @version 1.0
// * @description TODO
// * @date 2024/10/12 9:27
// */
////@SpringBootTest
////@Slf4j
//public class SpringAmqpTest {
//
////  @Resource
//  private RabbitTemplate rabbitTemplate;
//
//  //第一个入门程序
//  @Test
//  public void testSendMessage() {
//    //队列名称simple.queue
//    String queueName = "simple.queue";
//    String message = "hello,spring amqp!";
//    rabbitTemplate.convertAndSend(queueName, message);
//  }
//
//  /**
//   * workQueue
//   * 向队列中不停发送消息，模拟消息堆积。
//   */
//  @Test
//  public void testWorkQueue() throws InterruptedException {
//    // 队列名称
//    String queueName = "work.queue";
//    // 消息
//    String message = "hello, message_";
//    for (int i = 0; i < 50; i++) {
//      // 发送消息，每20毫秒发送一次，相当于每秒发送50条消息
//      rabbitTemplate.convertAndSend(queueName, message + i);
//      Thread.sleep(20);
//    }
//  }
//
//  @Test
//  public void testFanoutExchange() {
//    //交换机hmall.fanout
//    String exchangeName = "hmall.fanout";
//    String message = "hello,everyone";
//    rabbitTemplate.convertAndSend(exchangeName, "", message);
//  }
//
//  // 生产者 confirm return 机制
//  @Test
//  public void testSendDirectExchange() {
//    // 交换机名称
//    String exchangeName = "hmall.direct3";
//    // 消息
//    String message = "红色警报！日本乱排核废水，导致海洋生物变异，惊现哥斯拉！";
//    // 生产者 消息投递callback
////    rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
////      @Override
////      public void confirm(CorrelationData correlationData, boolean ack, String cause) {
////        if (ack) {
////          log.info("发送消息到交换机成功");
////        } else {
////          log.error("发送消息到交换机失败，原因：{}", cause); // no exchange 'hmall.direct3' in vhost '/hmall'
////        }
////      }
////    });
//
//    // 交换机路由 callback
////    rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
////      @Override
////      public void returnedMessage(ReturnedMessage returned) {
////         消息路由失败
////        log.error("发送消息到队列失败，原因：{}", returned.getReplyText()); //NO_ROUTE
////      }
////    });
//
//    // 发送消息
//    rabbitTemplate.convertAndSend(exchangeName, "blue2", message);
//  }
//
//  @Test
//  public void testSendTopicExchange() {
//    // 交换机名称
//    String exchangeName = "hmall.topic";
//    // 消息
//    String message = "喜报！孙悟空大战哥斯拉，胜!";
//    // 发送消息
//    rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
//  }
//
//  @Test
//  public void testSendMap() throws InterruptedException {
//    // 准备消息
//    Map<String, Object> msg = new HashMap<>();
//    msg.put("name", "柳岩");
//    msg.put("age", 21);
//    // 发送消息
//    rabbitTemplate.convertAndSend("object.queue", msg);
//  }
//
//  @Test
//  void testPublisherConfirm() {
//    // 1.创建CorrelationData
//    CorrelationData cd = new CorrelationData();
//    // 2.给Future添加ConfirmCallback
//    cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
//      @Override
//      public void onFailure(Throwable ex) {
//        // 2.1.Future发生异常时的处理逻辑，基本不会触发
//        log.error("send message fail", ex);
//      }
//
//      @Override
//      public void onSuccess(CorrelationData.Confirm result) {
//        // 2.2.Future接收到回执的处理逻辑，参数中的result就是回执内容
//        if (result.isAck()) { // result.isAck()，boolean类型，true代表ack回执，false 代表 nack回执
//          log.debug("发送消息成功，收到 ack!");
//        } else { // result.getReason()，String类型，返回nack时的异常描述
//          log.error("发送消息失败，收到 nack, reason : {}", result.getReason());
//        }
//      }
//    });
//    // 3.发送消息
//    rabbitTemplate.convertAndSend("hmall.directa", "q", "hello", cd);
//  }
//
//  //  注入 RabbitClient
//  @Resource
//  private RabbitClient rabbitClient;
//
//  @Test
//  void testPublisherReturn() {
//    rabbitClient.sendMsg("hmall.direct3", "q", "hello");
//
//    try {
//      TimeUnit.SECONDS.sleep(10);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Test
//  public void testLazyQueue() {
//    for (int i = 0; i < 100; i++) {
//      // 队列名称
//      String queueName = "lazy.queue";
//      // 消息
//      String message = "hello, spring amqp!" + i;
//      // 发送消息
//      rabbitTemplate.convertAndSend(queueName, message);
//    }
//
//  }
//
//
//  @Test
//  public void testPriorityQueue() throws InterruptedException {
//
//    for (int i = 0; i < 100; i++) {
//
//      // 设置消息优先级
//      int priority = new Random().nextInt(10) + 1; // 最大优先级为10
//      // 准备消息
//      String msg = "hello, priority queue! " + priority;
//      MessageProperties messageProperties = new MessageProperties();
//      messageProperties.setPriority(priority);
//
//      // 创建消息
//      Message message = MessageBuilder.withBody(msg.getBytes()).andProperties(messageProperties).build();
//
//      // 发送消息
//      rabbitTemplate.convertAndSend("priority.queue", message);
//    }
//
//  }
//
//  @Test
//  void testPublisherDelayMessage() {
//    // 1.创建消息
//    String message = "hello, delayed message";
//    // 2.发送消息，利用消息后置处理器添加消息头
//    rabbitTemplate.convertAndSend("delay.direct", "delay", message, new MessagePostProcessor() {
//      @Override
//      public Message postProcessMessage(Message message) throws AmqpException {
//        // 添加延迟消息属性
//        message.getMessageProperties().setDelay(5000);
//        log.info("发送消息" + new String(message.getBody(), StandardCharsets.UTF_8) + LocalDateTime.now());
//        return message;
//      }
//    });
//  }
//
//  // 发送 TTL 消息
//  @Test
//  void testSendTtlMessage() {
//    Message msg = MessageBuilder.withBody("abc".getBytes(StandardCharsets.UTF_8))
//        .setExpiration("5000")
//        .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
//        .build();
//
//    rabbitTemplate.convertAndSend("ttl.queue", msg);
//  }
//
//  @Test
//  public void testSendDelayMessage() {
//    Message message = MessageBuilder.withBody("abc".getBytes(StandardCharsets.UTF_8))
//        .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
//        .build();
//
//    rabbitTemplate.convertAndSend("ttl.fanout", "ttl", message);
//  }
//
//  @Test
//  public void testSendDelayMessage2() {
//    Message message = MessageBuilder.withBody("abc".getBytes(StandardCharsets.UTF_8))
//        .setHeader("x-delay", "20000")
//        .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
//        .build();
//    rabbitTemplate.convertAndSend("delay.exchange", "delay", message);
//  }
//
//}
