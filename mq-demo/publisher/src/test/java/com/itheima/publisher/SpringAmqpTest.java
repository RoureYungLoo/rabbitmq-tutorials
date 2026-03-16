package com.itheima.publisher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author luruoyang
 */
@SpringBootTest
@Slf4j
@DisplayName("简单队列模型和工作队列模型")
public class SpringAmqpTest {

  @Resource
  private RabbitTemplate rabbitTemplate;


  @Test
  public void testSimpleQueue() {
    String queueName = "simple.queue";
    String msg = "message";
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    rabbitTemplate.convertAndSend(queueName, msg + now);
    log.warn("消息发送成功");
  }

  @Test
  @DisplayName("工作队列模型")
  public void testWorkQueue() throws InterruptedException {
    String queueName = "work.queue";
    String msg = "hello, message_";
    for (int i = 0; i < 100; i++) {
      rabbitTemplate.convertAndSend(queueName, msg + i);
      Thread.sleep(20);
    }
  }

  @Test
  @DisplayName("延迟队列")
  public void testLazyQueue() {
    for (int i = 0; i < 100; i++) {
      String queueName = "lazy.queue";
      String msg = "hello, this is a message [" + i + "] to lazy queue";
      rabbitTemplate.convertAndSend(queueName, msg);
    }
  }

  @Test
  @DisplayName("优先级队列")
  public void testPriorityQueue() {
    String queueName = "priority.queue";
    String msg = "hello, a message with priority ";
    for (int i = 0; i < 100; i++) {
      int priority = new Random().nextInt(10) + 1;
      rabbitTemplate.convertAndSend(queueName, msg + priority, message -> {
        MessageProperties messageProperties = message.getMessageProperties();
        messageProperties.setPriority(priority);// 设置消息的优先级
        return message;
      });
    }
  }
}
