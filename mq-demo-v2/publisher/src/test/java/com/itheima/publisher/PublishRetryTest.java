//package com.itheima.publisher;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//
///**
// * @author luruoyang
// */
//@SpringBootTest
//@Slf4j
//public class PublishRetryTest {
//  @Resource
//  private RabbitTemplate rabbitTemplate;
//
//  @Test
////  @DisplayName("生产者重试")
//  public void testPublishRetry() {
//    String queueName = "simple.queue";
//    String msg = "this is a message .";
//    rabbitTemplate.convertAndSend(queueName,  msg);
//  }
//}
